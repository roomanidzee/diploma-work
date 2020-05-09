
CREATE TABLE IF NOT EXISTS mongo_profiles (
    _id String,
    user_id String,
    surname String,
    name String,
    patronymic String
) Engine = Dictionary(mongo_profiles_dictionary);

CREATE TABLE IF NOT EXISTS mongo_facilities(
    _id String,
    title String,
    speciality String
) Engine = Dictionary(mongo_facilities_dictionary);

CREATE TABLE IF NOT EXISTS mongo_graphods(
    _id String,
    facility_id String,
    profile_id String,
    groupmates Array(String)
) Engine = Dictionary(mongo_graphods_dictionary);

CREATE TABLE IF NOT EXISTS student_files(
    timestamp UInt64,
    username String,
    raw_password String,
    surname String,
    name String,
    patronymic String,
    email String,
    facility_title String,
    facility_graduation String,
    facility_speciality String,
    facility_group String,
    education_info String,
    competition_info String,
    underworking_info String,
    student_speciality_info String
) Engine = Kafka SETTINGS
            kafka_broker_list = 'std_kafka:9092',
            kafka_topic_list = 'student_file_source',
            kafka_group_name = 'student_files',
            kafka_format = 'JSONEachRow',
            kafka_num_consumers = 1;

CREATE TABLE IF NOT EXISTS student_files_stats(
    timestamp UInt64,
    record_time DateTime DEFAULT NOW(),
    username String,
    raw_password String,
    surname String,
    name String,
    patronymic String,
    email String,
    facility_title String,
    facility_graduation String,
    facility_speciality String,
    facility_group String,
    education_info String,
    competition_info String,
    underworking_info String,
    student_speciality_info String
) ENGINE = MergeTree()
ORDER BY timestamp;

CREATE MATERIALIZED VIEW IF NOT EXISTS student_files_consumer TO student_files_stats
  AS SELECT * FROM student_files;

CREATE TABLE IF NOT EXISTS requests_source(
    timestamp UInt64,
    vacancy_id String,
    employer_id String,
    worker_id String,
    request_status String
) Engine = Kafka SETTINGS
            kafka_broker_list = 'std_kafka:9092',
            kafka_topic_list = 'request_source',
            kafka_group_name = 'persons_requests',
            kafka_format = 'JSONEachRow',
            kafka_num_consumers = 1;

CREATE TABLE IF NOT EXISTS requests_stats(
    timestamp UInt64,
    record_time DateTime DEFAULT NOW(),
    vacancy_id String,
    employer_id String,
    worker_id String,
    request_status String
) ENGINE = MergeTree()
ORDER BY timestamp;

CREATE MATERIALIZED VIEW IF NOT EXISTS requests_consumer TO requests_stats
  AS SELECT * FROM requests_source;
