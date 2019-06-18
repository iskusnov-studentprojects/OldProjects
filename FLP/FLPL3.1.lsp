(defun decnestedlist (x)
	(cond
		((null x) ())
		((listp (car x)) (append (car x) (decnestedlist (cdr x))))
		(T (decnestedlist (cdr x)))
	)
)

(defun checknestedlists (x)
	(cond
		((listp (car x)) T)
		((null (cdr x)) nil)
		(T (checknestedlists (cdr x)))
	)
)

(defun fun (x)
	(cond
		((checknestedlists x) (fun (decnestedlist x)))
		(T (car x))
	)
)
