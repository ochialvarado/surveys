Create table "users" (
	"user_id" Integer PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1),
	"name" Varchar(200) NOT NULL,
	"password" Varchar(300) NOT NULL,
	"email" Varchar(200) NOT NULL,
	"start_date" Timestamp
);

Create table "surveys" (
	"survey_id" Integer PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1),
	"user_id" Integer NOT NULL,
	"title" Varchar(200) NOT NULL,
	"start_date" Timestamp NOT NULL,
	"description" Varchar(500),
	"end_date" Timestamp,
	"is_private" Boolean DEFAULT false,
	"is_visible" Boolean DEFAULT true
);

Create table "questions" (
	"question_id" Integer PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1),
	"answer_type_id" Integer NOT NULL,
	"survey_id" Integer NOT NULL,
	"title" Varchar(300) NOT NULL,
	"help" Varchar(20),
	"permalink" Varchar(300)
);

Create table "answer_types" (
	"answer_type_id" Integer PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1),
	"title" Varchar(200) NOT NULL,
	"value" Varchar(200)
);

Create table "question_options" (
	"question_option_id" Integer PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1),
	"question_id" Integer NOT NULL,
	"description" Varchar(300) NOT NULL,
	"value" Varchar(100) NOT NULL
);

Create table "survey_results" (
	"survey_result_id" Integer PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1),
	"edad_id" Integer NOT NULL,
	"survey_id" Integer NOT NULL,
	"provincia_id" Integer NOT NULL,
	"genero" Integer NOT NULL,
	"user_id" Integer,
	"creation_date" Timestamp,
	"is_anonymus" Boolean DEFAULT true
);

Create table "survey_question_answers" (
	"survey_question_answer_id" Integer PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1),
	"survey_result_id" Integer NOT NULL,
	"question_id" Integer NOT NULL,
	"text_answer" Varchar(500),
	"is_text" Boolean DEFAULT false
);

Create table "survey_question_answers_multiple" (
	"multiple_id" Integer PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1),
	"survey_question_answer_id" Integer NOT NULL,
	"question_option_id" Integer NOT NULL
);

Create table "edades" (
	"edad_id" Integer PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1),
	"age_range" Varchar(20) NOT NULL
);

Create table "provincia" (
	"provincia_id" Integer PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1),
	"name" Varchar(200) NOT NULL
);


Alter table "surveys" add Foreign Key ("user_id") references "users" ("user_id") on delete  restrict on update  restrict;
Alter table "questions" add Foreign Key ("survey_id") references "surveys" ("survey_id") on delete  restrict on update  restrict;
Alter table "survey_results" add Foreign Key ("survey_id") references "surveys" ("survey_id") on delete  restrict on update  restrict;
Alter table "question_options" add Foreign Key ("question_id") references "questions" ("question_id") on delete  restrict on update  restrict;
Alter table "survey_question_answers" add Foreign Key ("question_id") references "questions" ("question_id") on delete  restrict on update  restrict;
Alter table "questions" add Foreign Key ("answer_type_id") references "answer_types" ("answer_type_id") on delete  restrict on update  restrict;
Alter table "survey_question_answers_multiple" add Foreign Key ("question_option_id") references "question_options" ("question_option_id") on delete  restrict on update  restrict;
Alter table "survey_question_answers" add Foreign Key ("survey_result_id") references "survey_results" ("survey_result_id") on delete  restrict on update  restrict;
Alter table "survey_question_answers_multiple" add Foreign Key ("survey_question_answer_id") references "survey_question_answers" ("survey_question_answer_id") on delete  restrict on update  restrict;
Alter table "survey_results" add Foreign Key ("edad_id") references "edades" ("edad_id") on delete  restrict on update  restrict;
Alter table "survey_results" add Foreign Key ("provincia_id") references "provincia" ("provincia_id") on delete  restrict on update  restrict;