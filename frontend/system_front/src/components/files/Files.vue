<template>
    <div>

      <div class="file">
          <label class="file-label">
            <input 
              class="file-input"
              type="file"
              name="resume"
              ref="file"
              multiple
              v-on:change="handleFileUpload"/>
            <span class="file-cta">
              <span class="file-icon">
                <i class="fas fa-upload"></i>
              </span>
              <span class="file-label">
                  Загрузить файл
              </span>
            </span>
          </label>
      </div>

      <b-field class="file">
        <b-upload v-model="file">
            <a class="button is-primary" v-on:click="fileUpload()">
                <b-icon icon="upload"></b-icon>
                <span>Загрузить файл</span>
            </a>
        </b-upload>
        <span class="file-name" v-if="file">
            {{ file.name }}
        </span>
      </b-field>
      <div v-for="fileInfo in filesData" v-bind:key="fileInfo">
        <card
          v-bind:fileName="fileInfo.path"
          v-bind:fileType="fileInfo.typeInfo"
          v-bind:fileID="fileInfo.idValue"
        />
      </div>
    </div>
</template>

<script lang="ts">
import FilesCard from '@/components/files/files_card';
import { Component, Vue } from 'vue-property-decorator';
import { filesAppClient, serviceAppClient } from '@/components/common/services/clients';

interface FileInfo {
    idValue: string;
    path: string;
    typeInfo: string;
}

@Component({
  components: {
    'card': FilesCard,
  },
})
export default class FilesInfoComponent extends Vue{

  private filesData: FileInfo[] | null = null;

  public mounted(){

    const headers = {
      'Content-Type': 'application/json',
      'Authorization': sessionStorage.getItem('token')
    }

    const resp = serviceAppClient.get('/api/files/internal', headers);

    const filesResult = resp.then((respValue: any) => {
        this.filesData = respValue.data.foreach((elem: any) =>{
          const result : FileInfo = {
            idValue: elem.id,
            path: elem.path,
            typeInfo:  <string> elem.fileType.substring(<string> elem.fileType.lastIndexOf('/'))
          };
        });
    })

  }

  public handleFileUpload(e: Event){
    const target = e.target as HTMLInputElement;
    const file: File = (target.files as FileList)[0]

    let formData = new FormData();
    formData.append('input_file', file);
    const headers = {
      'Content-Type': 'multipart/form-data',
      'Authorization': sessionStorage.getItem('token')
    }
    filesAppClient.post('/api/files/upload', formData, headers)
                  .then((respValue: any) => {
                    if (respValue.status_code !== 200) {
                      console.error('failed to upload file');
                    }
                    return '';
                  });
  }
}
</script>
