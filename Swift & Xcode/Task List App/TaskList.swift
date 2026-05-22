//
//  TaskList.swift
//  beirne-jordan-Lab04
//
//  Created by Beirne, Jordan on 12/4/25.
//

import Foundation

class TaskList {
    var count : Int?
    var list : [Task] = []
    //The TaskList class must provide the following read-only property:
    // count — an integer representing the number of items in the list
    //The TaskList class must implement the following methods:
    public init() {
        count = 0
    }
    //Creates a new empty TaskList instance.
    public func completeTasks() -> [Task] {
        var completed : [Task] = []
        for Task in list {
            if Task.completed {
                completed.append(Task)
            }
        }
        return completed
    }
    //Returns an NSArray containing all Tasks which are marked completed.
    public func incompleteTasks() -> [Task] {
        var incomplete : [Task] = []
        for Task in list {
            if !Task.completed {
                incomplete.append(Task)
            }
        }
        return incomplete
    }
    //Returns an NSArray containing all Tasks which are not completed.
    public func allTasks() -> [Task] {
        return list
    }
    //Returns an NSArrary containing all Tasks in the list.
    public func pastDueTasks() -> [Task] {
        var currentDate : Date? = Calendar.current.startOfDay(for: Date())
        var late : [Task] = []
        for Task in list {
            if (Task.dueDate! as Date) < currentDate! {
                late.append(Task)
            }
        }
        return late
    }
    //Returns an NSArray consisting of classes which are past due. Past due tasks are defined as tasks which have a due date prior to
    //the current day. For example, a Task with a dueDate of 5:16:00pm on 3/11/2019 is not past due until the date rolls over to
    //3/12/2019.
    public func tasksBetween(start: Date, end: Date) -> [Task] {
        var tasksFound : [Task] = []
        for Task in list {
            if ((Task.dueDate! as Date) >= start) && ((Task.dueDate! as Date) <= end) {
                 tasksFound.append(Task)
            }
        }
        return tasksFound
    }
    //Returns an NSArray consisting of classes which are between the specified start and stop dates/times (inclusive on both sides).
    //These between times should be treated exactly as specified — no rounding to beginning or end of day.
    public func tasks(with p: Priority) -> [Task] {
        var priorityTasks : [Task] = []
        for Task in list {
            if Task.priority == .high {
                priorityTasks.append(Task)
            }
        }
        return priorityTasks
    }
    //Returns an NSArray containing all Tasks with a priority matching the specified Priority.
    public func add(task: Task) -> Bool {
        if !list.contains(task) {
            list.append(task)
            return true
        }
        else {
            return false
        }
        
    }
    //This method should add the specified Task to the list. If a Task already exists in the list where all properties match the
    //specified task, then the method should return NO and not insert anything. If no matching Task is found, the item should be
    //inserted and the method should return YES.
    public func removeAllTasks() {
        list = []
    }
    //This method should remove all Tasks from the list.
    public func remove(task: Task) -> Bool {
        for (index, Task) in list.enumerated() {
            if Task == task {
                list.remove(at: index)
            }
        }
        return false
        
        
    }
    //This method should remove the specified Task from the list. If a Task exists in the list where all properties match the
    //specified task, then the method should return YES and remove the item from the list. If no matching Task is found, then nothing
    //should be removed and the method should return NO.
    public func removeCompletedTasks() {
        list.removeAll { $0.completed }
    }
    //This method should remove all Tasks that are marked complete from the list.

}
