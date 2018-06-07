create table comment
(
	COMMENT_ID int not null
		primary key,
	PERSON_ID int null,
	CREATED_DATE datetime null,
	DEPTH int null,
	FILM_ID int null,
	PARENT_COMMENT_ID int null,
	TEXT varchar(255) not null,
	TITLE varchar(255) null,
	OWNER_ID int null
)
;

create table film
(
	FILM_ID int not null
		primary key,
	description varchar(255) not null,
	TITLE varchar(60) not null,
	YEAR int null
)
;

create table film_relation
(
	ID_FILM_RELATION int not null
		primary key,
	film_FILM_ID int null,
	person_PERSON_ID int not null,
	PERSON_ROLE_TYPE int null,
	ROLE varchar(100) null,
	personRoleDictionary_SL_PERSON_ROLE_ID int null,
	constraint FKfqfxsrdadhrp1h4sywwrdclmq
		foreign key (film_FILM_ID) references film (FILM_ID)
)
;

create index FKfqfxsrdadhrp1h4sywwrdclmq
	on film_relation (film_FILM_ID)
;

create index FKtijdyq0jyy5bn2d22wajdwljl
	on film_relation (PERSON_ID)
;

create index personRoleDictionary_SL_PERSON_ROLE_ID
	on film_relation (personRoleDictionary_SL_PERSON_ROLE_ID)
;

create table hibernate_sequence
(
	next_val bigint null
)
;

create table persistent_logins
(
	username varchar(64) not null,
	series varchar(64) not null
		primary key,
	token varchar(64) not null,
	last_used timestamp default CURRENT_TIMESTAMP not null
)
;

create table person
(
	PERSON_ID int not null
		primary key,
	BIOGRAPHY varchar(255) null,
	BORN_DATE date null,
	DIED_DATE date null,
	FIRST_NAME varchar(255) not null,
	LAST_NAME varchar(255) null
)
;

alter table film_relation
	add constraint FKtijdyq0jyy5bn2d22wajdwljl
		foreign key (person_PERSON_ID) references person (PERSON_ID)
;

create table rating
(
	ratinId int not null
		primary key,
	rating int null,
	filmId int null,
	user_id int null,
	constraint FKej8yur1ov17ypdv25grdrs3xo
		foreign key (filmId) references film (FILM_ID)
)
;

create index FKej8yur1ov17ypdv25grdrs3xo
	on rating (filmId)
;

create index user_id
	on rating (user_id)
;

create table rating_films
(
	rating_id bigint not null
		primary key,
	filmId int null,
	person_id int null,
	rating int null,
	user_id int null,
	constraint rating_films_film_FILM_ID_fk
		foreign key (filmId) references film (FILM_ID),
	constraint rating_films_person_PERSON_ID_fk
		foreign key (person_id) references person (PERSON_ID)
)
;

create index rating_films_film_FILM_ID_fk
	on rating_films (filmId)
;

create index rating_films_person_PERSON_ID_fk
	on rating_films (person_id)
;

create table roles
(
	id int not null
		primary key,
	role varchar(255) null
)
;

create table sl_genres
(
	SL_GENRES_ID int not null
		primary key,
	SL_GENRES_NAME varchar(255) null
)
;

create table sl_person_role
(
	SL_PERSON_ROLE_ID int not null
		primary key,
	SL_PERSON_ROLE_KEY varchar(255) null,
	SL_PERSON_ROLE_TYPE varchar(255) null
)
;

alter table film_relation
	add constraint personRoleDictionary_SL_PERSON_ROLE_ID
		foreign key (personRoleDictionary_SL_PERSON_ROLE_ID) references sl_person_role (SL_PERSON_ROLE_ID)
;

create table users
(
	id int not null
		primary key,
	email varchar(255) null,
	enabled bit null,
	password varchar(80) not null,
	username varchar(45) not null
)
;

alter table rating
	add constraint user_id
		foreign key (user_id) references users (id)
;

create table users_roles
(
	roles_id int not null,
	users_id int not null,
	constraint users_roles_roles_id_fk
		foreign key (roles_id) references roles (id),
	constraint users_roles_users_id_fk
		foreign key (users_id) references users (id)
)
;

create index users_roles_users_id_fk
	on users_roles (users_id)
;

create index users_roles_roles_id_fk
	on users_roles (roles_id)
;

create view new_view as 
SELECT
    `films`.`film`.`FILM_ID`     AS `ID`,
    `films`.`film`.`TITLE`       AS `TITLE`,
    `films`.`film`.`DESCRIPTION` AS `DESCRIPTION`,
    `films`.`film`.PUBLISH_YEAR        AS `YEAR`,
    `c`.`CREATED_DATE`           AS `created_date`,
    `c`.person_PERSON_ID              AS `person_id`,
    `c`.`TEXT`                   AS `text`,
    `af`.`actor_film_id`         AS `actor_film_id`
  FROM ((`films`.`film`
    JOIN `films`.PERSON_COMMENTS `c` ON ((`c`.`FILM_ID` = `films`.`film`.`FILM_ID`))) JOIN `films`.`actor_film` `af`
      ON ((`af`.`film_id` = `films`.`film`.`FILM_ID`)));

create procedure INSERT_RATING (IN in_user_id int, IN in_film_id int, IN in_person_id int, IN in_rating int)  
BEGIN
/*have to include checking if rating was already inserted*/
	/*SET out_result = 0;*/
    
       if in_person_id <> 0 then
			insert into rating_films(id, user_id, person_id, rating) 
			values
			(
				in_user_id, in_person_id, in_rating
			);
            
		elseif in_film_id <> 0 then
			insert into rating_films(user_id, film_id, rating)
			values
			(
				in_user_id, in_film_id, in_rating
			);

		
			/*set out_result = 1;*/
		end if;
        
END;

