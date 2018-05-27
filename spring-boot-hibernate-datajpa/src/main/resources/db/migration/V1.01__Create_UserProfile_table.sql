CREATE TABLE `mytestdb`.`user_profile` (
  `user_id` BIGINT NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `userProfile_userId_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mytestdb`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
