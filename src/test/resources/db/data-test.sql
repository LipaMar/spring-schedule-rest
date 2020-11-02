insert into students
values (1, 'Kaylee', 'Careswell', 'kcareswell0@eventbrite.com', '53whrKbJX', 5, 'HLA', 51573,
        'ROLE_STUDENT');
insert into students
values (2, 'Benedicto', 'Hanks', 'bhanks1@altervista.org', '9zX30CK', 2, 'DDE', 52124,
        'ROLE_STUDENT');

insert into meetings
values (1, 'quam turpis adipiscing lorem vitae mattis',
        'dolor quis odio consequat varius integer ac leo pellentesque ultrices mattis odio donec vitae nisi',
        'dolor vel est donec odio justo sollicitudin ut suscipit a feugiat et eros vestibulum ac est lacinia',
        '2021-09-24 20:04:25');
insert into meetings
values (2, 'nibh ligula nec sem duis aliquam', 'fermentum justo',
        'hac habitasse platea dictumst maecenas ut massa quis augue luctus tincidunt nulla mollis molestie lorem quisque ut erat curabitur',
        '2021-02-13 00:08:10');

insert into students_meetings (m_id, s_id)
values (1, 2);
insert into students_meetings (m_id, s_id)
values (2, 1);