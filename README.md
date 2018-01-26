# Introduction
This is a project for me to experiment and grasp the key concepts of domain driven design and onion architecture.

The problem domain is pattern matching, pattern can be set up that given these set of inputs matcher will return this output.

# Roadmap
1. rename matcher, clause, predicate to make it more meaningful
1. introduce matcher repository
1. introduce REST api to call matcher
1. create another bounded context - flow machine
    - integrate with matcher, test matcher's encapsulation
    - to experiment port and adapter
1. introduce valid values for predicate
    - potentially another bounded context
1. introduce other primitive type other than string
    - integer, double
    - refactoring
1. introduce operator other than equals
    - more than, less than, not equal, wildcard
    - refactoring
1. introduce matching the functional way
    - self-entertaining
    
# Todo
- do something about value object's ID, condition.id, consequent.id
- do something about empty default constructor
- do something about `new Consequent()`
- change unit test to in memory database
- move database transaction from repository to application service
- create matcher from specification?