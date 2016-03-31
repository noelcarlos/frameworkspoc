CREATE TABLE users (
  id INTEGER PRIMARY KEY,
  name VARCHAR(30),
  uid VARCHAR(50)
);

CREATE TABLE apps (
  id INTEGER PRIMARY KEY,
  name VARCHAR(30),
  loc_action_id INTEGER DEFAULT 2,
  dev_action_id INTEGER DEFAULT 2,
  int_action_id INTEGER DEFAULT 1,
  fdg_action_id INTEGER DEFAULT 2,
  pre_action_id INTEGER DEFAULT 2,
  pro_action_id INTEGER DEFAULT 2,
  is_active BOOLEAN
);

CREATE TABLE lk_action_types (
  id INTEGER PRIMARY KEY,
  name VARCHAR(30),
  is_active BOOLEAN
);

CREATE TABLE lk_environments (
  id INTEGER PRIMARY KEY,
  name VARCHAR(30),
  is_active BOOLEAN
);

CREATE TABLE lk_companies (
  id INTEGER PRIMARY KEY,
  name VARCHAR(30),
  is_active BOOLEAN
);

CREATE TABLE app_rules (
  id INTEGER PRIMARY KEY,
  app_id INTEGER,
  action_type_id INTEGER,
  environments_id INTEGER,
  company_id INTEGER
);

CREATE TABLE app_rule_logs (
  id INTEGER PRIMARY KEY,
  app_id INTEGER,
  action_type_id INTEGER,
  environments_id INTEGER,
  user_id INTEGER,
  creation_date TIMESTAMP
);

