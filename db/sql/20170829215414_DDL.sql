-- 用户基本信息 --
CREATE TABLE `user_info` (
  `id` VARCHAR(32) NOT NULL,
  `name` VARCHAR(64) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `salt` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`))
/

-- 角色信息 --
CREATE TABLE `role_info` (
  `id` VARCHAR(32) NOT NULL,
  `name` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`))
/

-- 用户和角色的关联关系 --
CREATE TABLE `user_role` (
  `user_id` VARCHAR(32) NOT NULL,
  `role_id` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`))
/

-- 权限信息 --
CREATE TABLE `permission_info` (
  `id` VARCHAR(32) NOT NULL,
  `name` VARCHAR(32) NOT NULL,
  `code` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC))
/

-- 角色和权限的关联关系 --
CREATE TABLE `role_permission` (
  `role_id` VARCHAR(32) NOT NULL,
  `permission_id` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`))
/

-- 班级信息 --
CREATE TABLE `class_info` (
  `id` VARCHAR(32) NOT NULL,
  `name` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`))
/

-- 学生和班级的关联关系 --
CREATE TABLE `class_student` (
  `class_id` VARCHAR(32) NOT NULL,
  `student_id` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`class_id`, `student_id`))
/

-- 年级信息 --
CREATE TABLE `grade_info` (
  `id` VARCHAR(32) NOT NULL,
  `name` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`))
/

-- 学生和班级的关联关系 --
CREATE TABLE `grade_student` (
  `grade_id` VARCHAR(32) NOT NULL,
  `student_id` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`grade_id`, `student_id`))
/

-- 课程信息 --
CREATE TABLE `course_info`(
	`id` VARCHAR(32) NOT NULL,
	`name` VARCHAR(64) NOT NULL,
	`description` VARCHAR(256) NOT NULL,
	`limit`	int NOT NULL DEFAULT 0,
	`teacher_id`		VARCHAR(32) NOT NULL,
	PRIMARY KEY(`id`))
/

-- 课程属性类型信息 --
CREATE TABLE `course_attribute_type`(
	`attribute_type` varchar(32) NOT NULL,
	`attribute_name` varchar(64) NOT NULL,
	PRIMARY KEY(`attribute_type`)
)
/

-- 课程和属性信息的关联关系 --
CREATE TABLE `course_attribute_info`(
	`course_id` VARCHAR(32) NOT NULL,
	`course_attribute_type` VARCHAR(32) NOT NULL,
	`course_attribute_value` VARCHAR(256) NOT NULL,
	PRIMARY KEY(`course_id`, `course_attribute_type`)
)
/

-- 课程和年级的关联关系 --
CREATE TABLE `course_grade`(
	`course_id` VARCHAR(32) NOT NULL,
	`grade_id` VARCHAR(32) NOT NULL,
	PRIMARY KEY(`course_id`, `grade_id`)
)
/

-- 学生的选课信息 --
CREATE TABLE `student_course_info`(
	`student_id` VARCHAR(32) NOT NULL,
	`course_id` VARCHAR(32) NOT NULL,
	PRIMARY KEY(`student_id`, `course_id`)
)
/

