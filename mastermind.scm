;数当てゲームです
;いわゆるヒット&ブロー
(define life 5)

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

;input 4 method
(define (input-4-numbers)
  (display "input four numbers\n>")
  (let loop ((num-list '()))
    (if (= (length num-list) 4)
    	(reverse num-list)
     	(let ((num (input-number)))
        	(cond ((not num)
                (delete-input-data)
                (input-4-numbers))
               	((member num num-list)
                 (display "same number error\n")
                 (delete-input-data)
                 (input-4-numbers))
                (else
                  (loop (cons num numlist))))))))