(import java.io.FileReader java.io.File)

(defmacro safeP [arg1 arg2]
	`(try 
		(let ~ arg1
			(try
				~arg2
				;(catch Exception e2# (str "Error: " (.getMessage e2#)))
				(finally
					(if (instance? java.io.Closeable ~(first arg1))
						(.close ~(first arg1))
					)
				)
			)
		)
		(catch Exception e# (str "Error: " (.getMessage e#)))
	)
)


(defn safen
	[arg1 arg2]
	(try
	    (eval (list 'let arg1 arg2))
        ;(println (.read arg1))
		(catch Exception e (str "Narmin is nice. " (.getMessage e)))
		;(finally (prn (instance? java.io.Closeable (first arg1))))
	)
)

;(def narmin )
;(println ('(new FileReader (new File "tekkjxt.txt")) '(.read)))

;(println (macroexpand '(safe [s (new FileReader (new File "text.txt"))] (.read s))))
(println (safeP [s (new FileReader (new File "text.txt"))] (.read s)))
;(println (safe [s (new FileReader (new File "text.txt"))] (.read s)))
;(safen '[s (new FileReader (new File "texasdasdt.txt"))] '(.read s))
;(try-me)
;(println (safe0 [s (new FileReader (new File "text.txt"))] (.read s)))
