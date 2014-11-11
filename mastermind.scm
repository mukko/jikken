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