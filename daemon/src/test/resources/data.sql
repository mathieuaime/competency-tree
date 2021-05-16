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
values (1, 'frontend', 'frontend description');

insert into skill(id, name, icon)
values (1, 'INTERNET', 'flip_to_front'),
       (2, 'BASIC SKILLS', 'flip_to_front');

insert into task(id, name, description)
values (1, 'Basics of HTTP', 'Learn the basics of HTTP'),
       (2, 'DNS', 'Learn how a DNS works'),
       (3, 'Learn HTML', 'Learn the basics of HTML');

insert into roadmap_item(roadmap_id, skill_id, task_id, required, category)
values (1, 1, 1, true, 'BASIC'),
       (1, 1, 2, false, 'BASIC'),
       (1, 2, 3, true, 'BEGINNER');

insert into user_check(user_id, roadmap_item_id, done)
values (1, 1, true);