--liquibase formatted sql

--changeset shumitum:1

create TABLE IF NOT EXISTS device
(
    device_id   varchar(16)                 not null,
    import_date timestamp(6) with time zone not null,
    description varchar(255),
    constraint device_pk primary key (device_id)
);

create TABLE IF NOT EXISTS track
(
    track_id            bigint generated by default as identity,
    video_id            bigint unique               not null,
    video_creation_date timestamp(6) with time zone not null,
    device_id           varchar                     not null,
    constraint track_pk primary key (track_id),
    constraint fk_track_to_device foreign key (device_id) references device (device_id)
        on delete restrict
        on update cascade
);

create index device_id_idx on track (device_id);

create TABLE IF NOT EXISTS point
(
    point_id       bigint generated by default as identity,
    video_id       bigint                      not null,
    bearing        double precision            not null,
    velocity       double precision            not null,
    point_datetime timestamp(6) with time zone not null,
    location       geometry(point, 4326)       not null,
    constraint point_pk primary key (point_id)
);

create index video_id_idx on point (video_id);