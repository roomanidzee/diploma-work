CREATE DATABASE studeeper_db;

CREATE TABLE studeeper_db.students_info(

    record_time DateTime DEFAULT NOW(),
    facility_title String,
    facility_graduation DateTime,
    facility_speciality String,
    profile_id String,
    groupmates Array(String)

)
ENGINE = MergeTree()
ORDER BY (profile_id, record_time)
TTL record_time + INTERVAL 1 HOUR;