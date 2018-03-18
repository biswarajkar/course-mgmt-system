CREATE TABLE `VerticalAlignment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));
  
CREATE TABLE `HorizontalAlignment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));

CREATE TABLE `HtmlWidget` (
  `widgetId` INT(11) NOT NULL,
  `htmlId` INT(11) NOT NULL AUTO_INCREMENT,
  `markupText` TEXT NULL,
  `maxCharacters` INT NULL,
  PRIMARY KEY (`htmlId`),
  INDEX `fk_html_widget_idx` (`widgetId` ASC),
  UNIQUE INDEX `widgetId_UNIQUE` (`widgetId` ASC),
  CONSTRAINT `fk_html_widget`
    FOREIGN KEY (`widgetId`)
    REFERENCES `Widget` (`widgetId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `GoogleDocWidget` (
  `widgetId` INT NOT NULL,
  `googleDocId` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(500) NULL,
  `editable` TINYINT NULL,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`googleDocId`),
  UNIQUE INDEX `widgetId_UNIQUE` (`widgetId` ASC),
  CONSTRAINT `fk_googleDoc_widget`
    FOREIGN KEY (`widgetId`)
    REFERENCES `Widget` (`widgetId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `ImageWidget` (
  `widgetId` INT NOT NULL,
  `imageId` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(500) NULL,
  `verticalAlign` VARCHAR(45) NULL,
  `horizontalAlign` VARCHAR(45) NULL,
  PRIMARY KEY (`imageId`),
  UNIQUE INDEX `widgetId_UNIQUE` (`widgetId` ASC),
  INDEX `image_valign_enum_ref_idx` (`verticalAlign` ASC),
  INDEX `image_halign_enuf_ref_idx` (`horizontalAlign` ASC),
  CONSTRAINT `fk_image_widget`
    FOREIGN KEY (`widgetId`)
    REFERENCES `Widget` (`widgetId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `image_valign_enum_ref`
    FOREIGN KEY (`verticalAlign`)
    REFERENCES `VerticalAlignment` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `image_halign_enuf_ref`
    FOREIGN KEY (`horizontalAlign`)
    REFERENCES `HorizontalAlignment` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);

CREATE TABLE `VideoWidget` (
  `widgetId` INT NOT NULL,
  `videoId` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(500) NULL,
  `youtubeId` VARCHAR(45) NULL,
  `expandable` TINYINT NULL,
  PRIMARY KEY (`videoId`),
  UNIQUE INDEX `widgetId_UNIQUE` (`widgetId` ASC),
  CONSTRAINT `fk_video_widget`
    FOREIGN KEY (`widgetId`)
    REFERENCES `Widget` (`widgetId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

