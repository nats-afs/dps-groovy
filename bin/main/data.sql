insert into users (id, username, password, enabled) values (1,'andres','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',true);
insert into users (id, username, password, enabled) values (2,'admin','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',true);

insert into authorities (user_id, authority) values (1,'ROLE_USER');
insert into authorities (user_id, authority) values (2,'ROLE_ADMIN');
insert into authorities (user_id, authority) values (2,'ROLE_USER');