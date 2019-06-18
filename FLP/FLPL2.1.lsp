(defun maxlist (x)
	(cond
		((null x) nil)
		((null (cdr x)) (car x))
		((> (car x) (cadr x)) (maxlist (cons (car x) (cddr x))))
		(T (maxlist (cdr x)))
	)
)

(defun countnum (x n)
	(cond
		((null (car x)) 0)
		((= (car x) n) (+ 1 (countnum (cdr x) n)))
		(T (countnum (cdr x) n))
	)
)

(defun makenewlist (x i m)
	(if (> i m) ()
		(cons (countnum x i) (makenewlist x (+ 1 i) m))
	)
)

(defun fun (x)
	(makenewlist x 1 (maxlist x))
)