(defun fun (x a)
	(prog (ret)
		(cond ((null x) (return nil)))
		(setq ret ())
		cycle
			(cond 
				((null x) (return ret))
				((eq (car x) a)
					(setq ret (append ret (list (car x))))
					(setq x (cddr x))
				)
				(T
					(setq ret (append ret (list (car x))))
					(setq x (cdr x))
				)
			)
		(go cycle)
	)
)