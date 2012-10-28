ALTER TABLE `users` DROP `admin`;
ALTER TABLE `users` DROP `canmodifyworld`;
ALTER TABLE `users` DROP `ignoresrestrictions`;

ALTER TABLE `users` ADD `admin/unrestricted` tinyint(1) DEFAULT 0;

INSERT INTO  `minecraft`.`items` (`name` ,`itemid`)

VALUES ('tripwire',  132),('sprucewoodstairs',  134),('birchwoodstairs',  135),('junglewoodstairs',  136),('commandblock',  137),
('beacon',  138),('cobblestonewall',  139),('flowerpot',  140),('carrots',  141),('potatoes',  142),('woodenbutton',  143),
('head',  144),('anvil',  145),('itemframe',  389),('flowerpot',  390),('carrot',  391),('potato',  392),('bakedpotato',  393),
('poisonouspotato',  394),('emptymap',  395),('goldencarrot',  396),('skull',  397),('carrotonastick',  398),
('netherstar',  399),('pumpkinpie',  400);