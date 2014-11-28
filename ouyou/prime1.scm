(define (check-prime n d)
	(if (> d (sqrt n))
		#t
		(if (zero? (modulo n d))
			#f
			(check-prime n (+ d 1)))))

(define (is-prime n)
	(if (< n 2)
		#f
		(check-prime n 2)))

(define prime?
	(lambda (n)
		(if (is-prime n)
			(print "素数です")
			(print "素数ではありません"))))