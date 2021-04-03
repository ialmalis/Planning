(define (domain HARBOR)
(:requirements :strips)
(:predicates 

(dock ?x) (truck ?x) (container ?x) (crane ?x)
(empty ?truck1) (full ?truck1 ?container1)
(at ?truck1 ?dock1) (on ?container1 ?container2)
(placed ?container1 ?dock1) (isconnected ?dock1 ?dock2)
(belongs ?crane1 ?truck1) (hold ?crane1 ?container1)
(free ?crane1) (clear ?container1) (freedock ?dock1) 
)

(:action move                                
     :parameters (?t ?from ?to ?cr )
     :precondition (and ( truck ?t ) (dock ?from) (dock ?to)
                        (at ?t ?from) (freedock ?to) (isconnected ?from ?to)(free ?cr))
     :effect (and (at ?t ?to) (freedock ?from)
		                (not (freedock ?to)) (not (at ?t ?from)) )
)

 
 (:action pickcontainerfromdock                                
     :parameters (?cr ?t ?c ?d)
     :precondition (and (truck ?t) (crane ?cr) (container ?c) (dock ?d)
                   (belongs ?cr ?t) (at ?t ?d) (placed ?c ?d) (clear ?c)
                   (free ?cr))
     :effect       (and  (hold ?cr ?c) (not (placed ?c ?d)) (not (free ?cr)))
     
 )
 
 ;; pick a container c which is placed on an another container c2
 
  (:action pickcontainer_from_on_another_container                                
     :parameters (?cr ?t ?c ?d ?c2)
     :precondition (and (truck ?t) (crane ?cr) (container ?c) (dock ?d)
                   (container ?c2) (belongs ?cr ?t) (at ?t ?d) (placed ?c ?d)
                   (placed ?c2 ?d) (on ?c ?c2)(clear ?c)(free ?cr))
     :effect       (and  (hold ?cr ?c) (not (free ?cr)) (not (placed ?c ?d)) 
                   (clear ?c2) (not (on ?c ?c2)))
     
 )
 

 
 (:action puttotruck                                
     :parameters (?cr ?c ?t )
     :precondition (and (truck ?t) (crane ?cr) (container ?c)
                        (belongs ?cr ?t) (hold ?cr ?c) (empty ?t))
                      
     :effect (and  (full ?t ?c) (not (empty ?t)) (free ?cr) 
                    (not (hold ?cr ?c)))
                    
)

 (:action pickcontainerfromtruck                               
     :parameters (?cr ?c ?t )
     :precondition (and (truck ?t) (crane ?cr) (container ?c)
                        (belongs ?cr ?t) (free ?cr) (full ?t ?c))
                      
     :effect (and  (hold ?cr ?c) (empty ?t) (not (free ?cr))
                    (not (full ?t ?c)))
                    
)


(:action puttodock                            
     :parameters (?cr ?c ?t ?d)
     :precondition (and (truck ?t) (crane ?cr) (container ?c) (dock ?d)
                        (belongs ?cr ?t) (at ?t ?d) (hold ?cr ?c)) 
                      
     :effect (and  (free ?cr) (not (hold ?cr ?c)) (placed ?c ?d) (clear ?c))
                    
                    
)

(:action puttodock_on_another_container                            
     :parameters (?cr ?c ?c2 ?t ?d)
     :precondition (and (truck ?t) (crane ?cr) (container ?c) (dock ?d) 
                    (container ?c2) (belongs ?cr ?t) 
                    (at ?t ?d) (placed ?c2 ?d) (clear ?c2)(hold ?cr ?c)) 
                      
     :effect (and  (free ?cr) (not (hold ?cr ?c)) (placed ?c ?d) (clear ?c) 
                (on ?c ?c2) (not(clear ?c2))   )
                    
                    
))


