-- 予定の初期データの投入
TRUNCATE plans;

INSERT INTO plans
(id, user_id, planned_at, start_at, end_at, title, description)
VALUES
(1, 1, '2022-03-15', '15:30', '16:30', '歯医者', '半年に一度の定期検診'),
(2, 1, '2022-03-18', '16:00', '17:00', '歯医者', '虫歯の治療<br>時間があれば、歯のクリーニング'),
(3, 2, '2022-03-18', NULL, NULL, '市役所、転出届', NULL),
(4, 2, '2022-04-06', NULL, NULL, '市役所', '転入届を出す<br>住民票をもらう'),
(5, 1, '2022-04-07', NULL, '15:00', '免許証の住所変更', NULL),
(6, 1, '2022-04-07', NULL, '15:00', '銀行', '給与振り込み用の口座を開設する<br>三菱UFJ、春日町支店'),
(7, 1, '2022-04-10', '18:30', NULL, '職場の歓迎会', NULL),
(8, 1, '2022-04-12', '10:30', '12:00', '健康診断', '新宿区検診センター'),
(9, 2, '2022-05-03', '10:00', NULL, '荒川区マラソン大会', NULL),
(10, 1, '2022-05-05', '15:30', NULL, '映画', '新宿TOHOシネマ');

-- ユーザーの初期データ投入
TRUNCATE users;

-- パスワードは、abcdをBCryptでハッシュ化したもの
INSERT INTO users
(id, name, login_id, login_pass)
VALUES
(1, '山田太郎', 'taro', '$2a$08$le8p8JMNAntQsIIREaLqoOBBFxIvg23aHRFK72JlExGsEo2qsCg36'),
(2, '田中花子', 'hana', '$2a$08$le8p8JMNAntQsIIREaLqoOBBFxIvg23aHRFK72JlExGsEo2qsCg36');