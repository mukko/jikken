;random-integerを使うために宣言
(use srfi-27)

;答えのリストを返す
;0 - 9　を重複なしでランダムに
(define (make-answer answer)
	(if (= (length answer) 4)
		answer
		(let ((num (random-integer 10)))
			(if (member num answer)
				(make-answer answer)
				(make-answer (cons num answer))))))

;答えのリストanswerの作成
(define answer (make-answer '()))
;Test
(display answer)

;Hit数を返す
(define (count-Hit answer data)
	(cond ((null? answer) 0)
		  ((= (car answer) (car data))
		  	(+ 1 (count-Hit (cdr answer) (cdr data))))
		  (else
		  	(count-Hit (cdr answer) (cdr data)))))

;Blow数を返す
;HitしていもBlowに含まれた数が返る
(define (count-Blow answer data)
	(cond ((null? answer) 0)
		  ((member (car answer) data)
		  (+ 1 (count-Blow (cdr answer) data)))
		  (else
		  	(count-Blow (cdr answer) data))))

;表示用
(define (display-hit-blow answer data hit)
	(display "Hit : ")
	(display hit)
	(display " ,Blow : ")
	(display (- (count-Blow answer data) hit))
	(newline)
	)

(define (display-first)
  (display "please input 4 numbers list! [0 - 9]\n"))
;単純に、リストの読み込みはreadを使って行う
;例外処理は考えない
(define (hit-blow answer)
	;(display "please input 4 numbers list! [0 - 9]\n")
	(display-first)
 	(let* ((data (read))
		(hit (count-Hit answer data)))
			(display-hit-blow answer data hit)
	))

(hit-blow answer)
