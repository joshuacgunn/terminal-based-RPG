public enum EnemyType {
    GOBLIN {
        @Override
        public String toString() {
            return "Goblin";
        }
    },
    ORC {
        @Override
        public String toString() {
            return "Orc";
        }
    },
    TROLL {
        @Override
        public String toString() {
            return "Troll";
        }
    },
    SKELETON {
        @Override
        public String toString() {
            return "Skeleton";
        }
    },
    WIZARD {
        @Override
        public String toString() {
            return "Wizard";
        }
    },
    NECROMANCER {
        @Override
        public String toString() {
            return "Necromancer";
        }
    },
    BOSS {
        @Override
        public String toString() {
            return "Boss";
        }
    },
}
