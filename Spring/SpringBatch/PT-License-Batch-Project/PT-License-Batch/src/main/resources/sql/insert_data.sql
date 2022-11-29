INSERT INTO package (package_name, count, period, created_at)
VALUES ('Starter PT 10회', 10, 60, '2022-11-01 00:00:00'),
       ('Starter PT 20회', 20, 120, '2022-11-01 00:00:00'),
       ('Starter PT 30회', 30, 180, '2022-11-01 00:00:00'),
       ('무료 이벤트 필라테스 1회', 1, NULL, '2022-11-01 00:00:00'),
       ('바디 챌린지 PT 4주', NULL, 28, '2022-11-01 00:00:00'),
       ('바디 챌린지 PT 8주', NULL, 48, '2022-11-01 00:00:00'),
       ('인바디 상담', NULL, NULL, '2022-11-01 00:00:00');

INSERT INTO `user` (user_id, user_name, status, phone, meta, created_at)
VALUES ('A1000000', '이준호', 'ACTIVE', '01011112222', NULL, '2022-11-01 00:00:00'),
       ('A1000001', '최봉석', 'ACTIVE', '01033334444', NULL, '2022-11-01 00:00:00'),
       ('A1000002', '장성엽', 'INACTIVE', '01055556666', NULL, '2022-11-01 00:00:00'),
       ('B1000010', '김성환', 'ACTIVE', '01077778888', NULL, '2022-11-01 00:00:00'),
       ('B1000011', '김준호', 'INACTIVE', '01088889999', NULL, '2022-11-01 00:00:00'),
       ('B2000000', '박재훈', 'ACTIVE', '01099990000', NULL, '2022-11-01 00:00:00'),
       ('B2000001', '김민수', 'ACTIVE', '01000001111', NULL, '2022-11-01 00:00:00');

INSERT INTO user_group_mapping (user_group_id, user_id, user_group_name, description, created_at)
VALUES ('IFBB', 'A1000000', 'ifbb', 'IFBB PRO MENS PHYSIQUE', '2022-11-01 00:00:00'),
       ('IFBB', 'A1000001', 'ifbb', 'IFBB PRO MENS PHYSIQUE', '2022-11-01 00:00:00'),
       ('IFBB', 'A1000002', 'ifbb', 'IFBB PRO CLASSIC PHYSIQUE', '2022-11-01 00:00:00'),
       ('IFBB', 'B1000010', 'ifbb', 'IFBB PRO 212 BODYBUILDING', '2022-11-01 00:00:00'),
       ('IFBB', 'B2000000', 'ifbb', 'IFBB PRO CLASSIC PHYSIQUE', '2022-11-01 00:00:00'),
       ('IFBB', 'B2000001', 'ifbb', 'IFBB PRO MENS PHYSIQUE', '2022-11-01 00:00:00');