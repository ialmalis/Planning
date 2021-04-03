(define (problem Harbor_problem)
(:domain HARBOR)
(:objects
dock1 dock2 dock3 con1 con2 con3 truck1 crane1 )
(:init

(dock dock1)(dock dock2)(dock dock3)
(container con1)(container con2)(container con3)
(truck truck1)(crane crane1) (belongs crane1 truck1) (at truck1 dock2) 
(freedock dock1)(freedock dock3)
(free crane1)(empty truck1)
(isconnected dock1 dock3)(isconnected dock3 dock1)
(isconnected dock1 dock2)(isconnected dock2 dock1)
(placed con1 dock1)(placed con3 dock1)(on con3 con1)(clear con3)
(placed con2 dock2)(clear con2)

)


(:goal (and

(at truck1 dock1) (placed con3 dock3)(placed con1 dock3)(placed con2 dock3)
(on con1 con2)(clear con3)(clear con1)




)
)
)