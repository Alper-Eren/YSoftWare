CREATE TABLE employee (
  identification_number VARCHAR(20) PRIMARY KEY,
  name VARCHAR(20) NOT NULL,
  surname VARCHAR(20) NOT NULL,
  salary NUMERIC(10,2) NOT NULL,
  project_id VARCHAR(20) NOT NULL,
  hire_date_time TIMESTAMP NOT NULL
);

ALTER TABLE employee ADD COLUMN employee_type VARCHAR(30);
ALTER TABLE employee ALTER COLUMN employee_type SET NOT NULL;

CREATE TABLE project (
    project_id VARCHAR(20) PRIMARY KEY,
    project_name VARCHAR(30) NOT NULL,
    start_date TIMESTAMP,
    finish_date TIMESTAMP,
    is_project_active BOOLEAN NOT NULL,
    manager_id VARCHAR(20),
    number_of_analyst INTEGER NOT NULL,
    number_of_designer INTEGER NOT NULL,
    number_of_programmer INTEGER NOT NULL,
    min_number_of_analyst INTEGER NOT NULL,
    min_number_of_designer INTEGER NOT NULL,
    min_number_of_programmer INTEGER NOT NULL,
    max_number_of_analyst INTEGER NOT NULL,
    max_number_of_designer INTEGER NOT NULL,
    max_number_of_programmer INTEGER NOT NULL,
    FOREIGN KEY (manager_id) REFERENCES employee (identification_number)
);

CREATE TABLE works_on(
  identification_number VARCHAR(20),
  project_id VARCHAR(20),
  FOREIGN KEY (identification_no) REFERENCES employee (identification_number),
  FOREIGN KEY (project_id) REFERENCES project(project_id)
)