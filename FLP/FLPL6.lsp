;Список кодов свойств
;0 - тип примитива
;10 - центр окружности
;40 - радиус

(defun c:osmode_off (/)
	(setq osmode_old_ (getvar "osmode") )
	(setvar "osmode" 0)
)

(defun  getallel ()
	(getallelh (entnext))
)
	
(defun getallelh (x)
	(cond
		((null x) ())
		((eq (gettype x) "CIRCLE") (append (list x) (getallelh (entnext x))))
		(T (getallelh (entnext x)))
	)
)

(defun getval (lst prop)
	(cond
		((null lst) nil)
		((eq prop (caar lst)) (cdar lst))
		(T (getval (cdr lst) prop))
	)
)

(defun gettype (x)
	(getval (entget x) 0)
)

(defun getrad (x)
	(getval (entget x) 40)
)

(defun getcenter (x)
	(getval (entget x) 10)
)

(defun ins_triangle (elements inrad)
	(while (not (null elements))
		(setq cur (car elements))
		(setq cen (getcenter cur))
		(setq rad (getrad cur))
		(if (> rad inrad)
			(progn
				(setq p1 (list (car cen) (- (cadr cen) rad)))
				(setq p2 (list (+ (car cen) (* rad (/ (sqrt 3) 2))) (+ (cadr cen) (* rad (/ (sqrt 1) 2)))))
				(setq p3 (list (- (car cen) (* rad (/ (sqrt 3) 2))) (+ (cadr cen) (* rad (/ (sqrt 1) 2)))))
			)
			(progn
				(setq p1 (list (car cen) (+ (cadr cen) rad)))
				(setq p2 (list (+ (car cen) (* rad (/ (sqrt 3) 2))) (- (cadr cen) (* rad (/ (sqrt 1) 2)))))
				(setq p3 (list (- (car cen) (* rad (/ (sqrt 3) 2))) (- (cadr cen) (* rad (/ (sqrt 1) 2)))))
			)
		)
		(command "отрезок" p1 p2 p3 p1 "")
		(setq elements (cdr elements))
	)
)

(defun fun (r)
  	(c:osmode_off)
	(ins_triangle (getallel) r)
	nil
)