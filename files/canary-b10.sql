ALTER TABLE `homes`  ADD `dimension` tinyint(1) NOT NULL DEFAULT '0';
ALTER TABLE `homes`  ADD `world` VARCHAR(32) NOT NULL;
ALTER TABLE `warps`  ADD `world` VARCHAR(32) NOT NULL;