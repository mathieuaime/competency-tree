delete
from user_check;
delete
from roadmap_item;
delete
from task;
delete
from skill;
delete
from roadmap;

insert into roadmap(id, name, description)
values (1, 'frontend', 'frontend'),
       (2, 'backend', 'backend'),
       (3, 'devops', 'devops'),
       (4, 'spring', 'spring'),
       (5, 'cdb', 'computer database');

insert into skill(id, name, icon)
values (1, 'INTERNET', 'flip_to_front'),
       (2, 'BASIC SKILLS', 'flip_to_front'),
       (3, 'BASIC TOOLS', 'flip_to_front'),
       (4, 'CSS FRAMEWORK', 'developer_mode'),
       (5, 'CSS PREPROCESSORS', 'view_quilt'),
       (6, 'CSS ARCHITECTURE', 'developer_board'),
       (7, 'CSS SKILLS', 'devices'),
       (8, 'CSS MASTERY', 'widgets'),
       (9, 'BASIC DOM', 'hdr_strong'),
       (10, 'WEB DRAWING', 'gradient'),
       (11, 'JAVASCRIPT SKILLS', 'format_quote'),
       (12, 'JAVASCRIPT FRAMEWORK', 'developer_mode'),
       (13, 'JAVASCRIPT PREPROCESSORS', 'nfc'),
       (14, 'TASK RUNNERS', 'import_contacts'),
       (15, 'BUILD TOOLS', 'table_chart'),
       (16, 'PACKAGE MANAGERS', 'device_hub'),
       (17, 'OPERATING SYSTEM', 'flip_to_front'),
       (18, 'HOSTING SERVICES', 'devices'),
       (19, 'DATABASE MASTERY', 'devices'),
       (20, 'RELATIONAL DATABASES', 'devices'),
       (21, 'NOSQL DATABASES', 'devices'),
       (22, 'CLOUD', 'devices'),
       (23, 'TEST I', 'devices'),
       (24, 'DESIGN PRINCIPLES', 'devices'),
       (25, 'ARCHITECTURAL PATTERNS', 'devices'),
       (26, 'SEARCH ENGINES', 'devices'),
       (27, 'MESSAGE BROKERS', 'devices'),
       (28, 'SCALING', 'devices'),
       (38, 'SECURITY', 'devices'),
       (39, 'SECURITY I', 'devices'),
       (40, 'SECURITY II', 'devices'),
       (41, 'SERVER SIDE RENDERING', 'devices'),
       (42, 'STATIC SITE GENERATOR', 'devices'),
       (43, 'CACHING', 'devices'),
       (44, 'JAVA FRAMEWORK', 'developer_mode'),
       (45, 'LOGGING', 'devices'),
       (46, 'CONTINUOUS INTEGRATION', 'devices'),
       (47, 'CONTINUOUS DEPLOYMENT', 'devices'),
       (48, 'SCRIPTING', 'devices'),
       (49, 'TEST II', 'devices'),
       (50, 'CI / CD', 'devices'),
       (51, 'WEB SKILLS', 'devices'),
       (52, 'DATABASE SKILLS', 'devices'),
       (53, 'CERTIFICATIONS', 'devices'),
       (54, 'CERTIFICATIONS I', 'devices'),
       (55, 'CERTIFICATIONS II', 'devices'),
       (56, 'OTHER LANGAGES', 'devices'),
       (57, 'INSTALLATION', 'devices'),
       (58, 'SPRING', 'devices');

insert into task(id, name, description)
values (1, 'Basics of HTTP', ''),
       (2, 'DNS', ''),
       (3, 'Learn HTML', ''),
       (4, 'Basics of CSS', ''),
       (5, 'Basics of JavaScript', ''),
       (6, 'Git', ''),
       (8, 'Text Editor', ''),
       (9, 'Basic Terminal Usage', ''),
       (10, 'Bootstrap', ''),
       (11, 'UIKit', ''),
       (12, 'Foundation', ''),
       (13, 'Semantic UI', ''),
       (14, 'Sass', ''),
       (15, 'PostCSS', ''),
       (16, 'Less', ''),
       (17, 'Stylus', ''),
       (18, 'OOCSS', ''),
       (19, 'SMACSS', ''),
       (20, 'BEM', ''),
       (21, 'Responsive', ''),
       (22, 'Flexbox', ''),
       (23, 'Grid Layout', ''),
       (24, 'Animation', ''),
       (25, 'Transform 2D, 3D', ''),
       (26, 'jQuery', ''),
       (27, 'SVG', ''),
       (28, 'Canvas', ''),
       (29, 'D3.js', ''),
       (30, 'ES6', ''),
       (31, 'Vue.js',
        'Vue.js is an open-source model–view–viewmodel front end JavaScript framework for building user interfaces and single-page applications. It was created by Evan You, and is maintained by him and the rest of the active core team members coming from various companies such as Netlify and Netguru.'),
       (32, 'Angular',
        'Angular is a TypeScript-based open-source web application framework led by the Angular Team at Google and by a community of individuals and corporations. Angular is a complete rewrite from the same team that built AngularJS.'),
       (33, 'React.js',
        'React.js is an open-source, front end, JavaScript library for building user interfaces or UI components. It is maintained by Facebook and a community of individual developers and companies. React can be used as a base in the development of single-page or mobile applications. However, React is only concerned with rendering data to the DOM, and so creating React applications usually requires the use of additional libraries for state management and routing.'),
       (34, 'TypeScript', ''),
       (35, 'Babel', ''),
       (36, 'CoffeeScript', ''),
       (37, 'NPM', ''),
       (38, 'YARN', ''),
       (39, 'Bower', ''),
       (40, 'npm scripts', ''),
       (41, 'gulp', ''),
       (42, 'grunt', ''),
       (43, 'Webpack', ''),
       (44, 'Parcel', ''),
       (46, 'Learn Java', ''),
       (47, 'Java EE', ''),
       (48, 'Spring', ''),
       (49, 'Quarkus', ''),
       (50, 'Micronaut', ''),
       (51, 'MySQL', ''),
       (52, 'MariaDB', ''),
       (53, 'PostgreSQL', ''),
       (54, 'Oracle', ''),
       (55, 'MS SQL', ''),
       (56, 'JPA', ''),
       (57, 'ORM', ''),
       (58, 'Transactions', ''),
       (59, 'MongoDB', ''),
       (60, 'DynamoDB', ''),
       (61, 'CouchDB', ''),
       (62, 'Neo4j', ''),
       (63, 'REST', ''),
       (64, 'SOAP', ''),
       (65, 'OCA', ''),
       (66, 'OCP', ''),
       (67, 'ACID', ''),
       (68, 'Jenkins', ''),
       (69, 'Gitlab CI', ''),
       (70, 'Ansible',
        'Ansible is an open-source software provisioning, configuration management, and application-deployment tool enabling infrastructure as code.'),
       (71, 'Kubernetes', ''),
       (72, 'Python', ''),
       (73, 'Bash', ''),
       (74, 'Design Patterns', ''),
       (75, 'Domain Driven Design', ''),
       (76, 'Test Driven Development', ''),
       (77, 'SOLID', ''),
       (78, 'Behavior Driven Development', ''),
       (79, 'Unit Testing', ''),
       (80, 'AWS', ''),
       (81, 'Elasticsearch', ''),
       (82, 'ActiveMQ', ''),
       (83, 'RabbitMQ', ''),
       (84, 'Kafka', ''),
       (85, 'Docker', ''),
       (86, 'Docker Compose', ''),
       (87, 'JAX-RS', ''),
       (88, 'Spring MVC', ''),
       (89, 'Spring Data JPA', ''),
       (90, 'Spring Security', ''),
       (91, 'Jest', ''),
       (92, 'Mocha', ''),
       (93, 'Jasmine', ''),
       (94, 'Cypress', ''),
       (95, 'Enzyme', ''),
       (96, 'Cucumber', ''),
       (97, 'Monolithic Apps', ''),
       (98, 'Microservices', ''),
       (99, 'SOA', ''),
       (100, 'Serverless', ''),
       (101, 'OWASP', ''),
       (102, 'CORS', ''),
       (103, 'OAuth 2.0', ''),
       (104, 'Mockito', ''),
       (105, 'Integration Testing', ''),
       (106, 'Kotlin', ''),
       (107, 'Scala', ''),
       (108, 'Groovy', ''),
       (109, 'JUnit', ''),
       (110, 'ZeroMQ', ''),
       (111, 'Maven', ''),
       (112, 'Gradle', ''),
       (113, 'Liquibase', ''),
       (114, 'Flyway', ''),
       (115, 'N+1 Problem', ''),
       (116, 'JWT', ''),
       (117, 'Software Architecture', ''),
       (118, 'Singleton', ''),
       (119, 'Inversion Of Control', ''),
       (120, 'Slf4j', ''),
       (121, 'Logback', ''),
       (122, 'Data Transfer Object', ''),
       (123, 'Servlet', ''),
       (124, 'JSP', ''),
       (125, 'JSTL', ''),
       (126, 'Pagination', ''),
       (127, 'HikariCP', ''),
       (128, 'JDBCTemplate', ''),
       (129, 'i18n', ''),
       (130, 'Hibernate', ''),
       (131, 'HQL', ''),
       (132, 'QueryDSL', ''),
       (133, 'Criteria', ''),
       (134, 'Maven Modules', ''),
       (135, 'Gatling', ''),
       (136, 'Jooq', '');

-- Frontend basic
insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (1, 1, 1, true, 'BASIC'),
       (1, 1, 2, true, 'BASIC'),
       (1, 2, 3, true, 'BASIC'),
       (1, 2, 4, true, 'BASIC'),
       (1, 2, 5, true, 'BASIC'),
       (1, 3, 6, true, 'BASIC'),
       (1, 3, 8, true, 'BASIC'),
       (1, 3, 9, true, 'BASIC');

-- Frontend beginner
insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (1, 4, 10, true, 'BEGINNER'),
       (1, 4, 11, false, 'BEGINNER'),
       (1, 4, 12, false, 'BEGINNER'),
       (1, 4, 13, false, 'BEGINNER'),
       (1, 23, 91, true, 'BEGINNER'),
       (1, 23, 92, false, 'BEGINNER'),
       (1, 23, 93, false, 'BEGINNER'),
       (1, 7, 21, true, 'BEGINNER'),
       (1, 7, 22, true, 'BEGINNER'),
       (1, 9, 26, true, 'BEGINNER'),
       (1, 11, 30, true, 'BEGINNER'),
       (1, 12, 31, false, 'BEGINNER'),
       (1, 12, 32, false, 'BEGINNER'),
       (1, 12, 33, true, 'BEGINNER');

-- Frontend confirmed
insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (1, 5, 14, true, 'CONFIRMED'),
       (1, 5, 15, true, 'CONFIRMED'),
       (1, 5, 16, false, 'CONFIRMED'),
       (1, 5, 17, false, 'CONFIRMED'),
       (1, 13, 34, false, 'CONFIRMED'),
       (1, 13, 35, false, 'CONFIRMED'),
       (1, 13, 36, false, 'CONFIRMED'),
       (1, 16, 37, true, 'CONFIRMED'),
       (1, 16, 38, true, 'CONFIRMED'),
       (1, 16, 39, false, 'CONFIRMED'),
       (1, 14, 40, true, 'CONFIRMED'),
       (1, 14, 41, true, 'CONFIRMED'),
       (1, 14, 42, false, 'CONFIRMED'),
       (1, 49, 94, false, 'CONFIRMED'),
       (1, 49, 95, false, 'CONFIRMED'),
       (1, 49, 105, true, 'CONFIRMED');

-- Frontend expert
insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (1, 6, 18, false, 'EXPERT'),
       (1, 6, 19, false, 'EXPERT'),
       (1, 6, 20, true, 'EXPERT'),
       (1, 8, 23, false, 'EXPERT'),
       (1, 8, 24, false, 'EXPERT'),
       (1, 8, 25, false, 'EXPERT'),
       (1, 10, 27, true, 'EXPERT'),
       (1, 10, 28, true, 'EXPERT'),
       (1, 10, 29, false, 'EXPERT'),
       (1, 15, 43, true, 'EXPERT'),
       (1, 15, 44, false, 'EXPERT');

-- Backend basic
insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (2, 1, 1, true, 'BASIC'),
       (2, 1, 2, true, 'BASIC'),
       (2, 2, 3, true, 'BASIC'),
       (2, 2, 4, true, 'BASIC'),
       (2, 2, 5, true, 'BASIC'),
       (2, 2, 46, true, 'BASIC'),
       (2, 3, 6, true, 'BASIC'),
       (2, 3, 8, true, 'BASIC'),
       (2, 3, 9, true, 'BASIC'),
       (2, 54, 65, true, 'BASIC');

-- backend beginner
insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (2, 44, 47, true, 'BEGINNER'),
       (2, 44, 48, true, 'BEGINNER'),
       (2, 44, 49, false, 'BEGINNER'),
       (2, 44, 50, false, 'BEGINNER'),
       (2, 20, 51, true, 'BEGINNER'),
       (2, 20, 52, false, 'BEGINNER'),
       (2, 20, 53, false, 'BEGINNER'),
       (2, 20, 54, false, 'BEGINNER'),
       (2, 20, 55, false, 'BEGINNER'),
       (2, 52, 56, true, 'BEGINNER'),
       (2, 52, 57, true, 'BEGINNER'),
       (2, 52, 67, false, 'BEGINNER'),
       (2, 52, 58, false, 'BEGINNER'),
       (2, 51, 63, true, 'BEGINNER'),
       (2, 51, 64, false, 'BEGINNER'),
       (2, 23, 76, false, 'BEGINNER'),
       (2, 51, 87, true, 'BEGINNER'),
       (2, 23, 104, true, 'BEGINNER'),
       (2, 23, 109, true, 'BEGINNER'),
       (2, 55, 66, true, 'BEGINNER');

-- backend confirmed
insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (2, 48, 72, true, 'CONFIRMED'),
       (2, 48, 73, false, 'CONFIRMED'),
       (2, 24, 74, true, 'CONFIRMED'),
       (2, 24, 75, false, 'CONFIRMED'),
       (2, 24, 77, true, 'CONFIRMED'),
       (2, 39, 101, false, 'CONFIRMED'),
       (2, 39, 102, false, 'CONFIRMED'),
       (2, 39, 116, false, 'CONFIRMED'),
       (2, 49, 105, true, 'CONFIRMED'),
       (2, 49, 96, false, 'CONFIRMED'),
       (2, 49, 78, false, 'CONFIRMED'),
       (2, 56, 106, false, 'CONFIRMED'),
       (2, 56, 107, false, 'CONFIRMED'),
       (2, 56, 108, false, 'CONFIRMED'),
       (2, 16, 111, true, 'CONFIRMED'),
       (2, 16, 112, false, 'CONFIRMED');

-- backend expert
insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (2, 19, 113, true, 'EXPERT'),
       (2, 19, 114, true, 'EXPERT'),
       (2, 19, 115, true, 'EXPERT'),
       (2, 21, 59, true, 'EXPERT'),
       (2, 21, 60, false, 'EXPERT'),
       (2, 21, 61, false, 'EXPERT'),
       (2, 21, 62, false, 'EXPERT'),
       (2, 26, 81, true, 'EXPERT'),
       (2, 27, 82, false, 'EXPERT'),
       (2, 27, 83, true, 'EXPERT'),
       (2, 27, 84, true, 'EXPERT'),
       (2, 27, 110, false, 'EXPERT'),
       (2, 46, 85, true, 'EXPERT'),
       (2, 47, 86, false, 'EXPERT'),
       (2, 25, 97, true, 'EXPERT'),
       (2, 25, 98, true, 'EXPERT'),
       (2, 25, 99, false, 'EXPERT'),
       (2, 25, 100, false, 'EXPERT'),
       (2, 40, 103, true, 'EXPERT');

-- spring basic
insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (4, 2, 46, true, 'BASIC'),
       (4, 2, 47, true, 'BASIC');

-- spring beginner
insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (4, 51, 88, true, 'BEGINNER'),
       (4, 52, 89, true, 'BEGINNER');

-- spring confirmed
insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (4, 39, 90, true, 'CONFIRMED');

-- cdb basic
insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (5, 3, 6, true, 'BASIC'),
       (5, 3, 9, true, 'BASIC'),
       (5, 3, 8, true, 'BASIC'),
       (5, 3, 51, true, 'BASIC'),
       (5, 2, 46, true, 'BASIC');

-- cdb beginner
insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (5, 24, 117, false, 'BEGINNER'),
       (5, 24, 118, false, 'BEGINNER'),
       (5, 24, 119, false, 'BEGINNER'),
       (5, 45, 120, true, 'BEGINNER'),
       (5, 45, 121, true, 'BEGINNER'),
       (5, 51, 122, true, 'BEGINNER'),
       (5, 51, 123, true, 'BEGINNER'),
       (5, 51, 124, true, 'BEGINNER'),
       (5, 51, 126, true, 'BEGINNER'),
       (5, 16, 111, true, 'BEGINNER'),
       (5, 16, 134, false, 'BEGINNER'),
       (5, 16, 112, false, 'BEGINNER'),
       (5, 23, 104, true, 'BEGINNER'),
       (5, 23, 109, true, 'BEGINNER');

-- cdb confirmed
insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (5, 51, 88, true, 'CONFIRMED'),
       (5, 51, 63, true, 'CONFIRMED'),
       (5, 51, 87, true, 'CONFIRMED'),
       (5, 52, 58, true, 'CONFIRMED'),
       (5, 52, 127, true, 'CONFIRMED'),
       (5, 52, 128, true, 'CONFIRMED'),
       (5, 52, 130, true, 'CONFIRMED'),
       (5, 44, 48, true, 'CONFIRMED'),
       (5, 24, 129, true, 'CONFIRMED'),
       (5, 38, 90, true, 'CONFIRMED'),
       (5, 38, 116, false, 'CONFIRMED');

-- cdb expert
insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (5, 19, 131, true, 'EXPERT'),
       (5, 19, 132, false, 'EXPERT'),
       (5, 19, 133, false, 'EXPERT'),
       (5, 19, 136, false, 'EXPERT'),
       (5, 19, 89, false, 'EXPERT'),
       (5, 53, 65, true, 'EXPERT'),
       (5, 53, 66, true, 'EXPERT'),
       (5, 12, 31, false, 'EXPERT'),
       (5, 12, 32, false, 'EXPERT'),
       (5, 12, 33, false, 'EXPERT'),
       (5, 49, 105, false, 'EXPERT'),
       (5, 49, 135, false, 'EXPERT'),
       (5, 56, 106, false, 'EXPERT'),
       (5, 56, 107, false, 'EXPERT'),
       (5, 25, 98, false, 'EXPERT'),
       (5, 25, 100, false, 'EXPERT'),
       (5, 50, 68, false, 'EXPERT'),
       (5, 50, 80, false, 'EXPERT'),
       (5, 50, 85, false, 'EXPERT');