(defun countlen (x)
	(if (null (cdr x)) 1
		(+ 1 (countlen (cdr x)))
	)
)

(defun fun (x)
	(cond
		((null x) nil)
		((listp (car x))
			(cond
				((= 1 (countlen (car x))) (append (fun (car x)) (fun (cdr x))))
				(T (append (list (fun (car x))) (fun (cdr x))))
			)
		)
		(T (cons (car x) (fun (cdr x))))
	)
)