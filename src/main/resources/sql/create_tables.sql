-- DB作成
CREATE DATABASE myscheduler_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

USE myscheduler_db;

-- テーブルの作成
CREATE TABLE plans (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  planned_at DATE NOT NULL,
  start_at TIME,
  end_at TIME,
  title VARCHAR(30) NOT NULL,
  description VARCHAR(255)
);

CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  login_id VARCHAR(30) UNIQUE NOT NULL,
  login_pass CHAR(60) NOT NULL
);