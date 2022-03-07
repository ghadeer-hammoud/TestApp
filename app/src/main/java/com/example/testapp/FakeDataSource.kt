package com.example.testapp

import com.example.testapp.models.Level
import com.example.testapp.models.Task

class FakeDataSource {

    companion object{
        fun getFakeData(): ArrayList<Level>{

            var tasks1 = ArrayList<Task>()
            tasks1.add(Task(1, "Level 1 - Task 1", false))
            tasks1.add(Task(1, "Level 1 - Task 2", false))
            tasks1.add(Task(1, "Level 1 - Task 3", false))

            var tasks2 = ArrayList<Task>()
            tasks2.add(Task(1, "Level 2 - Task 1", false))
            tasks2.add(Task(1, "Level 2 - Task 2", false))
            tasks2.add(Task(1, "Level 2 - Task 3", false))

            var tasks3 = ArrayList<Task>()
            tasks3.add(Task(1, "Level 3 - Task 1", false))
            tasks3.add(Task(1, "Level 3 - Task 2", false))
            tasks3.add(Task(1, "Level 3 - Task 3", false))

            var tasks4 = ArrayList<Task>()
            tasks4.add(Task(1, "Level 4 - Task 1", false))
            tasks4.add(Task(1, "Level 4 - Task 2", false))
            tasks4.add(Task(1, "Level 4 - Task 3", false))

            var tasks5 = ArrayList<Task>()
            tasks5.add(Task(1, "Level 5 - Task 1", false))
            tasks5.add(Task(1, "Level 5 - Task 2", false))
            tasks5.add(Task(1, "Level 5 - Task 3", false))


            val level1 = Level(
                null,
                1,
                "Level 1",
                "Level 1 descriptions goes here",
                tasks1,
                isLocked = false,
                isConfirmed = false
            )

            val level2 = Level(
                null,
                2,
                "Level 2",
                "Level 2 descriptions goes here",
                tasks2,
                isLocked = true,
                isConfirmed = false
            )

            val level3 = Level(
                null,
                3,
                "Level 3",
                "Level 3 descriptions goes here",
                tasks3,
                isLocked = true,
                isConfirmed = false
            )

            val level4 = Level(
                null,
                4,
                "Level 4",
                "Level 4 descriptions goes here",
                tasks4,
                isLocked = true,
                isConfirmed = false
            )

            val level5 = Level(
                null,
                5,
                "Level 5",
                "Level 5 descriptions goes here",
                tasks5,
                isLocked = true,
                isConfirmed = false
            )

            val levels = ArrayList<Level>()
            levels.add(level1)
            levels.add(level2)
            levels.add(level3)
            levels.add(level4)
            levels.add(level5)

            return levels;
        }
    }
}