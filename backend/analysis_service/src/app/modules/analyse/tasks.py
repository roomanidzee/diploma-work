
from typing import Tuple
from pathlib import Path

import nengo
import nengo_dl
import tensorflow as tf

import numpy as np
import pandas as pd

from app.utils import base_path
from app.modules.common import run_in_executor

def snn_model_template(x_shape: int, y_shape: int) -> Tuple[nengo.Network, nengo.Probe]:

    out_probe = None

    with nengo.Network(seed=1) as net:
        net.config[nengo.Ensemble].max_rates = nengo.dists.Choice([100])
        net.config[nengo.Ensemble].intercepts = nengo.dists.Choice([0])
        net.config[nengo.Connection].synapse = None
        neuron_type = nengo.LIF(amplitude=0.01)
    
        nengo_dl.configure_settings(stateful=False)

        inp = nengo.Node(np.zeros(x_shape * y_shape))

        layer = nengo_dl.Layer(
            tf.keras.layers.Conv1D(
                filters=32,
                kernel_size=3
            )
        )(
            inp,
            shape_in=(x_shape, y_shape, 1)
        )

        layer = nengo_dl.Layer(neuron_type)(layer)

        layer = nengo_dl.Layer(
            tf.keras.layers.Conv1D(
                filters=64,
                strides=2,
                kernel_size=3
            )
        )(
            layer,
            shape_in=(x_shape - 2, y_shape - 2, 32)
        )

        layer = nengo_dl.Layer(neuron_type)(layer)

        layer = nengo_dl.Layer(
            tf.keras.layers.Conv1D(
                filters=128,
                strides=2,
                kernel_size=3
            )
        )(
            layer,
            shape_in=(x_shape - 16, y_shape - 16, 64)
        )

        layer = nengo_dl.Layer(neuron_type)(layer)

        out = nengo_dl.Layer(tf.keras.layers.Dense(units=10))(layer)
        out_probe = nengo.Probe(out, label='out_probe')

    return net, out_probe

def get_models_path() -> Path:
    return Path(base_path) / Path('ml_data')

def get_data_and_labels(input_data: pd.DataFrame) -> Tuple[np.ndarray, np.ndarray]:

    labels = input_data[['label']].to_numpy()
    input_data.drop(['label'], axis=1)

    analysing_data = input_data[list(input_data.columns)].to_numpy()

    return analysing_data, labels

@run_in_executor
def prepare_model(ctx: dict, file_name: str, input_data: pd.DataFrame) -> str:

    analysing_data, labels =get_data_and_labels(input_data)

    x = input_data.shape[0]
    y = input_data.shape[1]

    net, out_probe = snn_model_template(x, y)

    batch_size = 100
    sim = nengo_dl.Simulator(net, minibatch_size=batch_size)

    sim.compile(
        optimizer=tf.optimizers.RMSprop(0.001),
        loss={out_probe: tf.losses.SparseCategoricalCrossentropy(from_logits=True)}
    )

    sim.fit(analysing_data, {out_probe: labels}, epochs=10)

    model_params_file = f"{file_name}.params"

    path = get_models_path() / Path(model_params_file)

    sim.save_params(str(path))

    return str(path)

async def build_model(ctx: dict, file_name: str, input_data: pd.DataFrame) -> str:
    return await prepare_model(ctx, file_name, input_data)
