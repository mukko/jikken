;数当てゲームです
;いわゆるヒット&ブロー

;乱数を作る
;ライブラリで作る
;実装の際はjavaでランダムとかかな
(use srfi-27)
;今回の上限は9なので10を設定

;answer is empty list
(define (make-answer answer)
    (if (= (length answer) 4)
      	answer
           (let ((num (random-integer 10)))
             (if (member num answer)
                 (make-answer answer)
                 (make-answer (cons num answer))))))

(display (make-answer '()))

;input method
(define (input-number)
  (let ((num (read)))
    (cond ((not (integer? num))
           (display "input integer [0 - 9]\n")
           #f)
          ((<= 0 num 9) num)
          (else
            (display "input [0 - 9]\n")
            #f))))

;delete
;#\ : express String type
(define (delete-input-data)
  (let ((c (read-char)))
    (if (not (char=? #\newline c))
        (delete-input-data)
        )))

;input 4 method
(define (input-4-numbers)
  (let loop ((num-list '()))
    (if (= (length num-list) 4)
    	  (reverse num-list)
     	  (let ((num (input-number)))
          (display num-list)
        	(cond ((not num)
                 (delete-input-data)
                 (input-4-numbers))
               	((member num num-list)
                 (display "same number error\n")
                 (delete-input-data)
                 (input-4-numbers))
                (else
                  (loop (cons num num-list))))))))

(define (input-four-numbers)
  (let ((num (input-number)))
    (cond #|((not num)
           (display "repeat\n")
           (input-four-numbers)
           )|#
          (else
            num))))

;count Blow
;Blow   : right number, right place
;answer : answer list
;data   : input data
(define (count-Blow answer data)
  (cond ((null? answer) 0)
        ((= (car answer) (car data))
         (+ 1 (count-Blow (cdr answer) (cdr data))))
        (else
          (count-Blow (cdr answer) (cdr data)))))

;count Hit
;Hit    : right number, different place
;answer : answer list
;data   : input data
;return : number of hits
(define (count-Hit answer data)
  (cond ((null? answer) 0)
        ((member (car answer) data)
         (+ 1 (count-Hit (cdr answer) data)))
        (else
          (count-Hit (cdr answer) data))))
 
;display Data
 (define (display-hit-blow count answer data blow)
   (display count)
   (display "\n")
   (display "Blow : ")
   (display blow)
   (display ", Hit : ")
   (display (- (count-Hit answer data) blow))
   (newline))
 
 
 #|(define (play answer)
   (let loop ((count 1))
     (let* ((data (input-4-numbers))
            (blow (count-Blow answer data)))
       (display-hit-blow count answer data blow)
       (cond ((= blow 4)
              (display "Congratulation!\n"))
             (else
               (loop (+ count 1)))))))
 |#
 ;(play (make-answer '()))