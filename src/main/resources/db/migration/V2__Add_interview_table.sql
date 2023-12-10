CREATE TABLE IF NOT EXISTS interview (
  id CHAR(36),
  user_id CHAR(36),
  title VARCHAR(255) NOT NULL,
  resume TEXT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE IF NOT EXISTS question (
  id CHAR(36),
  interview_id CHAR(36),
  content TEXT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (interview_id) REFERENCES interview (id)
);

CREATE TABLE IF NOT EXISTS answer (
  id CHAR(36),
  question_id CHAR(36),
  content TEXT NOT NULL,
  feedback TEXT,
  followup TEXT,
  PRIMARY KEY (id),
  FOREIGN KEY (question_id) REFERENCES question (id)
);
