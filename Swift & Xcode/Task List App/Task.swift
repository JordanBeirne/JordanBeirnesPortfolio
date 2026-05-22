//
//  Task.swift
//  beirne-jordan-Lab04
//
//  Created by Beirne, Jordan on 12/2/25.
//

import Foundation

enum Priority {
case low
case medium
case high
}

class Task: Equatable {
    var text: NSString?
    var dueDate: NSDate?
    var priority: Priority
    var completed: Bool
    
    public init() {
        self.text = nil
        self.dueDate = nil
        self.priority = .medium
        self.completed = false
    }
    
    public init(text: String, dueDate: Date, priority: Priority, completed: Bool) {
        self.text = text as NSString
        self.dueDate = dueDate as NSDate
        self.priority = priority
        self.completed = completed
    }
    
    public convenience init(_ text: String,_ dueDate: Date,_ priority: Priority,_ completed: Bool) {
        self.init()
        self.text = text as NSString
        self.dueDate = dueDate as NSDate
        self.priority = priority
        self.completed = completed
    }
    
    static func == (lhs: Task, rhs: Task) -> Bool {
        return lhs.text == rhs.text && lhs.dueDate == rhs.dueDate && lhs.priority == rhs.priority && lhs.completed == rhs.completed
    }
}
