CREATE TABLE user_data(
    id bigserial NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    goal text,
    age integer,
    CONSTRAINT user_data_pkey PRIMARY KEY (id)
);


CREATE TABLE generated_cv(
    id bigserial NOT NULL,
    file_name character varying(255),
    file_url character varying(255),
    user_data_id bigint,
    CONSTRAINT generated_cv_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user_data FOREIGN KEY (user_data_id) REFERENCES user_data(id)
);

CREATE TABLE user_experience(
    id bigserial NOT NULL,
    company_name character varying(255),
    position character varying(255),
    month_from integer,
    year_from integer,
    month_to integer,
    year_to integer,
    user_data_id bigint,
    CONSTRAINT user_experience_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user_data FOREIGN KEY (user_data_id) REFERENCES user_data(id)
);