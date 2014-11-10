;数当てゲームです
;いわゆるヒット&ブロー
(define life 5)

;乱数を作る
;ライブラリで作る
;実装の際はjavaでランダムとかかな
(use srfi-27)
;今回の上限は9なので10を設定
(print (random-integer 10))
