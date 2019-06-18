(defun fun (x a)
	(cond
		((null x) ())
		((eq (car x) a) (cons (car x) (fun (cddr x) a)))
		(T (cons (car x) (fun (cdr x) a)))
	)
)