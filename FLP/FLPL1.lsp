(defun dist
	(x y)
	(sqrt
		(+
			(* x x)
			(* y y)
		)
	)
)

(defun mindist
	(pos1 pos2)
	(min 
		(dist (car pos1) (cadr pos1))
		(dist (car pos2) (cadr pos2))
	)
)