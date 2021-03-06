CREATE TABLE `poker`.`combination_matrix`(
    `CLUB_TWO` BOOLEAN NOT NULL DEFAULT FALSE,
    `DIAMOND_TWO` BOOLEAN NOT NULL DEFAULT FALSE,
    `HEART_TWO` BOOLEAN NOT NULL DEFAULT FALSE,
    `SPADE_TWO` BOOLEAN NOT NULL DEFAULT FALSE,
    `CLUB_THREE` BOOLEAN NOT NULL DEFAULT FALSE,
    `DIAMOND_THREE` BOOLEAN NOT NULL DEFAULT FALSE,
    `HEART_THREE` BOOLEAN NOT NULL DEFAULT FALSE,
    `SPADE_THREE` BOOLEAN NOT NULL DEFAULT FALSE,
    `CLUB_FOUR` BOOLEAN NOT NULL DEFAULT FALSE,
    `DIAMOND_FOUR` BOOLEAN NOT NULL DEFAULT FALSE,
    `HEART_FOUR` BOOLEAN NOT NULL DEFAULT FALSE,
    `SPADE_FOUR` BOOLEAN NOT NULL DEFAULT FALSE,
    `CLUB_FIVE` BOOLEAN NOT NULL DEFAULT FALSE,
    `DIAMOND_FIVE` BOOLEAN NOT NULL DEFAULT FALSE,
    `HEART_FIVE` BOOLEAN NOT NULL DEFAULT FALSE,
    `SPADE_FIVE` BOOLEAN NOT NULL DEFAULT FALSE,
    `CLUB_SIX` BOOLEAN NOT NULL DEFAULT FALSE,
    `DIAMOND_SIX` BOOLEAN NOT NULL DEFAULT FALSE,
    `HEART_SIX` BOOLEAN NOT NULL DEFAULT FALSE,
    `SPADE_SIX` BOOLEAN NOT NULL DEFAULT FALSE,
    `CLUB_SEVEN` BOOLEAN NOT NULL DEFAULT FALSE,
    `DIAMOND_SEVEN` BOOLEAN NOT NULL DEFAULT FALSE,
    `HEART_SEVEN` BOOLEAN NOT NULL DEFAULT FALSE,
    `SPADE_SEVEN` BOOLEAN NOT NULL DEFAULT FALSE,
    `CLUB_EIGHT` BOOLEAN NOT NULL DEFAULT FALSE,
    `DIAMOND_EIGHT` BOOLEAN NOT NULL DEFAULT FALSE,
    `HEART_EIGHT` BOOLEAN NOT NULL DEFAULT FALSE,
    `SPADE_EIGHT` BOOLEAN NOT NULL DEFAULT FALSE,
    `CLUB_NINE` BOOLEAN NOT NULL DEFAULT FALSE,
    `DIAMOND_NINE` BOOLEAN NOT NULL DEFAULT FALSE,
    `HEART_NINE` BOOLEAN NOT NULL DEFAULT FALSE,
    `SPADE_NINE` BOOLEAN NOT NULL DEFAULT FALSE,
    `CLUB_TEN` BOOLEAN NOT NULL DEFAULT FALSE,
    `DIAMOND_TEN` BOOLEAN NOT NULL DEFAULT FALSE,
    `HEART_TEN` BOOLEAN NOT NULL DEFAULT FALSE,
    `SPADE_TEN` BOOLEAN NOT NULL DEFAULT FALSE,
    `CLUB_J` BOOLEAN NOT NULL DEFAULT FALSE,
    `DIAMOND_J` BOOLEAN NOT NULL DEFAULT FALSE,
    `HEART_J` BOOLEAN NOT NULL DEFAULT FALSE,
    `SPADE_J` BOOLEAN NOT NULL DEFAULT FALSE,
    `CLUB_Q` BOOLEAN NOT NULL DEFAULT FALSE,
    `DIAMOND_Q` BOOLEAN NOT NULL DEFAULT FALSE,
    `HEART_Q` BOOLEAN NOT NULL DEFAULT FALSE,
    `SPADE_Q` BOOLEAN NOT NULL DEFAULT FALSE,
    `CLUB_K` BOOLEAN NOT NULL DEFAULT FALSE,
    `DIAMOND_K` BOOLEAN NOT NULL DEFAULT FALSE,
    `HEART_K` BOOLEAN NOT NULL DEFAULT FALSE,
    `SPADE_K` BOOLEAN NOT NULL DEFAULT FALSE,
    `CLUB_A` BOOLEAN NOT NULL DEFAULT FALSE,
    `DIAMOND_A` BOOLEAN NOT NULL DEFAULT FALSE,
    `HEART_A` BOOLEAN NOT NULL DEFAULT FALSE,
    `SPADE_A` BOOLEAN NOT NULL DEFAULT FALSE,
    `hand_ranking` INT NOT NULL,
    `sub_rank` INT NOT NULL,
    INDEX(`CLUB_TWO`),
    INDEX(`DIAMOND_TWO`),
    INDEX(`HEART_TWO`),
    INDEX(`SPADE_TWO`),
    INDEX(`CLUB_THREE`),
    INDEX(`DIAMOND_THREE`),
    INDEX(`HEART_THREE`),
    INDEX(`SPADE_THREE`),
    INDEX(`CLUB_FOUR`),
    INDEX(`DIAMOND_FOUR`),
    INDEX(`HEART_FOUR`),
    INDEX(`SPADE_FOUR`),
    INDEX(`CLUB_FIVE`),
    INDEX(`DIAMOND_FIVE`),
    INDEX(`HEART_FIVE`),
    INDEX(`SPADE_FIVE`),
    INDEX(`CLUB_SIX`),
    INDEX(`DIAMOND_SIX`),
    INDEX(`HEART_SIX`),
    INDEX(`SPADE_SIX`),
    INDEX(`CLUB_SEVEN`),
    INDEX(`DIAMOND_SEVEN`),
    INDEX(`HEART_SEVEN`),
    INDEX(`SPADE_SEVEN`),
    INDEX(`CLUB_EIGHT`),
    INDEX(`DIAMOND_EIGHT`),
    INDEX(`HEART_EIGHT`),
    INDEX(`SPADE_EIGHT`),
    INDEX(`CLUB_NINE`),
    INDEX(`DIAMOND_NINE`),
    INDEX(`HEART_NINE`),
    INDEX(`SPADE_NINE`),
    INDEX(`CLUB_TEN`),
    INDEX(`DIAMOND_TEN`),
    INDEX(`HEART_TEN`),
    INDEX(`SPADE_TEN`),
    INDEX(`CLUB_J`),
    INDEX(`DIAMOND_J`),
    INDEX(`HEART_J`),
    INDEX(`SPADE_J`),
    INDEX(`CLUB_Q`),
    INDEX(`DIAMOND_Q`),
    INDEX(`HEART_Q`),
    INDEX(`SPADE_Q`),
    INDEX(`CLUB_K`),
    INDEX(`DIAMOND_K`),
    INDEX(`HEART_K`),
    INDEX(`SPADE_K`),
    INDEX(`CLUB_A`),
    INDEX(`DIAMOND_A`),
    INDEX(`HEART_A`),
    INDEX(`SPADE_A`)
) ENGINE = InnoDB