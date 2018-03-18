CREATE TABLE `Layout` (
  `layoutId` INT NOT NULL AUTO_INCREMENT,
  `courseId` INT NOT NULL,
  `themeId` INT NULL,
  `name` VARCHAR(100) NULL,
  `title` VARCHAR(200) NULL,
  `description` VARCHAR(500) NULL,
  `createDate` DATETIME NOT NULL DEFAULT NOW(),
  `updateDate` DATETIME NULL,
  `customBackground` VARCHAR(500) NULL,
  `stylesheetLink` VARCHAR(500) NULL,
  PRIMARY KEY (`layoutId`),
  INDEX `fk_layout_course_idx` (`courseId` ASC),
  INDEX `fk_layout_theme_idx` (`themeId` ASC),
  CONSTRAINT `fk_layout_course`
  	FOREIGN KEY (`courseId`)
  	REFERENCES `Course` (`courseId`)
  	ON DELETE CASCADE
  	ON UPDATE CASCADE,
  CONSTRAINT `fk_layout_theme`
  	FOREIGN KEY (`themeId`)
  	REFERENCES `Theme` (`themeId`)
  	ON DELETE SET NULL
  	ON UPDATE CASCADE);

CREATE TABLE `Page` (
  `pageId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `tooltipDescription` VARCHAR(500) NULL,
  `createDate` DATETIME NOT NULL DEFAULT NOW(),
  `updateDate` DATETIME NULL,
  `verticalOrder` INT NOT NULL,
  PRIMARY KEY (`pageId`),
  UNIQUE KEY `pageId_verticalOrder_UNIQUE` (`pageId` ASC, `verticalOrder` ASC));

CREATE TABLE `LayoutPage` (
  `layoutPageId` INT NOT NULL AUTO_INCREMENT,
  `layoutId` INT NOT NULL,
  `pageId` INT NOT NULL,
  PRIMARY KEY (`layoutPageId`),
  INDEX `layout_page_association_idx` (`pageId` ASC),
  INDEX `page_layout_association_idx` (`layoutId` ASC),
  UNIQUE KEY `layoutId_pageId_UNIQUE` (`layoutId` ASC, `pageId` ASC),
  CONSTRAINT `layout_page_association`
    FOREIGN KEY (`pageId`)
    REFERENCES `Page` (`pageId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `page_layout_association`
    FOREIGN KEY (`layoutId`)
    REFERENCES `Layout` (`layoutId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `Tab` (
  `tabId` INT NOT NULL AUTO_INCREMENT,
  `pageId` INT NOT NULL,
  `name` VARCHAR(100) NULL,
  `horizontalOrder` INT NOT NULL,
  PRIMARY KEY (`tabId`),
  UNIQUE KEY `tabId_horizontalOrder_UNIQUE` (`tabId` ASC,`horizontalOrder` ASC),
  INDEX `fk_tab_page_idx` (`pageId` ASC),
  CONSTRAINT `fk_tab_page` 
  	FOREIGN KEY (`pageId`) 
  	REFERENCES  `Page` (`pageId`) 
  	ON DELETE CASCADE 
  	ON UPDATE CASCADE);
  	
CREATE TABLE `Widget` (
  `widgetId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `topPosition` FLOAT NULL,
  `bottomPosition` FLOAT NULL,
  `leftPosition` FLOAT NULL,
  `rightPosition` FLOAT NULL,
  `width` FLOAT NULL,
  `height` FLOAT NULL,
  `foregroundColor` VARCHAR(45) NULL,
  `backgroundColor` VARCHAR(45) NULL,
  `cssClass` VARCHAR(50) NULL,
  `cssStyle` VARCHAR(500) NULL,
  `scrollable` TINYINT NULL,
  `fitContents` TINYINT NULL,
  PRIMARY KEY (`widgetId`));
  
CREATE TABLE `TabWidget` (
  `tabWidgetId` INT NOT NULL AUTO_INCREMENT,
  `tabId` INT NOT NULL,
  `widgetId` INT NOT NULL,
  PRIMARY KEY (`tabWidgetId`),
  INDEX `tab_widget_association_idx` (`widgetId` ASC),
  INDEX `widget_tab_association_idx` (`tabId` ASC),
  UNIQUE KEY `tabId_widgetId_UNIQUE` (`tabId` ASC, `widgetId` ASC),
  CONSTRAINT `tab_widget_association`
    FOREIGN KEY (`widgetId`)
    REFERENCES `Widget` (`widgetId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `widget_tab_association`
    FOREIGN KEY (`tabId`)
    REFERENCES `Tab` (`tabId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
  
