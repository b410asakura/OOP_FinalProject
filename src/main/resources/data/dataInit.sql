insert into users(id, first_name, last_name, email, password, role)
values (1, 'Lanie', 'Netherclift', 'lanie@gmail.com', '$2a$12$R.qe5//Z/PB9fjLbu/Ii/.zuDfLuYCIbI9UKGLKWFMmMv11dTDiG6',
        'USER'),
       (2, 'Jahanna', 'Longmaid', 'johanna@gmail.com', '$2a$04$uFhANYPBEqOCX9v0NIV2neGHFwJ3Pus0DhHsKIJ38IvURPGExTsWy',
        'USER'),
       (3, 'Emogene', 'Dunkerton', 'emogene@gmail.com', '$2a$04$YQn5lMXf7ZVVYi3DszmBFet2WwnAiEyb8GGcF27Pd8nTYtxah9PHu',
        'USER'),
       (4, 'Cate', 'Prigmore', 'cate@gmail.com', '$2a$04$0pMwcS3xf4ppe3sQf3u5A.StZQC2EIKlfgSqO7ABkz9.NLJUg7/.e',
        'USER'),
       (5, 'Harper', 'Aloway', 'harper@gmail.com', '$2a$04$dYLVO835AWMTKRi665Gh2.m.We/iMj9cnpG2qANek0UKZMZ3132WW',
        'USER');

insert into tests(id, title, description, enable, duration)
values (1, 'Test number 1', 'Test for first lesson', false, 240),
       (2, 'Test number 2', 'Examination test', false, 240),
       (3, 'Test number 3', 'Test to repeat last lesson', false, 240);

insert into questions(id, title, duration, enable, question_type, test_id)
values (1, 'Select the real english words', 60, false,
        'SELECT_REAL_ENGLISH_WORD', 1);

insert into questions(id, title, duration, enable, question_type, test_id)
values (2, 'Listen and select English words', 60, false,
        'LISTEN_AND_SELECT_ENGLISH_WORDS', 1);

insert into questions(id, title, duration, attempts, enable, file_url, question_type, test_id)
values (3, 'Write what you hear', 60, 2, false,
        'https://billingual-10.s3.eu-central-1.amazonaws.com/1701946842008ashnikko_-_daisy.mp3',
        'TYPE_WHAT_YOU_HEAR', 1);

insert into questions(id, title, duration, enable, file_url, question_type, correct_answer, test_id)
values (4, 'Describe the image', 60, false,
        'https://www.keralatourism.org/images/enchanting_kerala/large/bonacaud_thiruvananthapuram20221107144733_1242_1.jpg',
        'DESCRIBE_IMAGE',
        'One big elephant', 1);

insert into questions(id, title, duration, enable, statement, question_type, test_id)
values (5, 'Record yourself saying the statement below', 60, false, '
Literally I went and got lost',
        'RECORD_SAYING_STATEMENT', 2);

insert into questions(id, title, duration, enable, statement, question_type, attempts, test_id)
values (6, 'Respond in at least N words', 60, false, 'Describe a time you were surprised, what happened?',
        'RESPOND_AT_LEAST_N_WORDS', 30, 2);

insert into questions(id, title, duration, enable, passage, statement, question_type, correct_answer, test_id)
values (7, 'Highlight the answer', 60, false, 'What did resident think could happen with new bridge',
        'One of statement in question with id: 7 highlight the answer ', 'HIGHLIGHT_THE_ANSWER',
        'correct answer is highlight the answer', 2);

insert into questions(id, title, duration, enable, passage, question_type, test_id)
values (8, 'Select the main idea in this options', 60, false, 'This passage for question select the main idea',
        'SELECT_THE_MAIN_IDEA', 2),
       (9, 'Select the best title in this options', 60, false, 'This passage for question select the best title',
        'SELECT_THE_BEST_TITLE', 2);

insert into options(id, title, is_true, audio_url, question_id)
values (1, 'first option', true, null,1),
       (2, 'second option', false,null,1),
       (3, 'third option', false,null,1),
       (4, 'fourth option', true,null,1),
       (5, 'fifth option', false, null,1),
       (6, 'firstOp', true, 'https://billingual-10.s3.eu-central-1.amazonaws.com/1701946842008ashnikko_-_daisy.mp3',2),
       (7, 'secondOp', false, 'https://billingual-10.s3.eu-central-1.amazonaws.com/1701946842008ashnikko_-_daisy.mp3',2),
       (8, 'thirdOp', true, 'https://billingual-10.s3.eu-central-1.amazonaws.com/1701946842008ashnikko_-_daisy.mp3',2),
       (9, 'forthOp', true, 'https://billingual-10.s3.eu-central-1.amazonaws.com/1701946842008ashnikko_-_daisy.mp3',2),
       (10,'fifthOp', false,'https://billingual-10.s3.eu-central-1.amazonaws.com/1701946842008ashnikko_-_daisy.mp3',2),
       (11,'STMI first option',true,null,8),
       (12,'STMI second option',false,null,8),
       (13,'STMI third option',false,null,8),
       (14,'STMI forth option',false,null,8),
       (15,'STBT first option',true,null,9),
       (16,'STBT second option',false,null,9),
       (17,'STBT third option',false,null,9),
       (18,'STBT forth option',false,null,9);

insert into answers(id,statement,audio_file,question_type,user_id,question_id)
values (1,null, null, 'SELECT_REAL_ENGLISH_WORD', 1,1),
       (2,null, null, 'LISTEN_AND_SELECT_ENGLISH_WORDS', 1,2),
       (3,'Correct answer for question Type what you hear',null,'TYPE_WHAT_YOU_HEAR',1,3),
       (4,'Correct answer for question Describe image', null, 'DESCRIBE_IMAGE',1,4),
       (5,null, 'https://billingual-10.s3.eu-central-1.amazonaws.com/1699881836884drive-breakbeat-173062.mp3','RECORD_SAYING_STATEMENT',1,5),
       (6,'Respond in at least N words statement dont worry',null,'RESPOND_AT_LEAST_N_WORDS',1,6),
       (7,'Highlighted statement in text in this question but its not end',null,'HIGHLIGHT_THE_ANSWER',1,7),
       (8,null,null,'SELECT_THE_MAIN_IDEA',1,8),
       (9,null,null,'SELECT_THE_BEST_TITLE',1,9);

insert into options_users(options_id, users_id)
values (1,1),
 (3,1),
 (4,1),
 (6,1),
 (8,1),
 (9,1),
 (11,1),
 (14,1),
 (3,2),
 (2,2),
 (1,2),
 (7,2),
 (8,2),
 (9,2);

insert into results(id, date_of_submission, checked,  score, answer_id)
values (1, '2023-11-11',true,10, 1),
       (2, '2023-11-11', true,  10,  2),
       (3, '2023-11-11', true,  10, 3),
       (4, '2023-11-11', true,  10,  4),
        (5, '2023-11-11', true,  10,  5),
        (6, '2023-11-11', true,  10,  6),
        (7, '2023-11-11', true,  10,  7),
         (8, '2023-11-11', true, 10, 8),
          (9, '2023-11-11', true,  10,  9);

insert into users_tests(users_id, tests_id)
values (1, 1);