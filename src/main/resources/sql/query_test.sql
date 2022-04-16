-- id:1 山田さんの2022年4月のスケジュールを取得
SELECT * FROM plans
WHERE
  planned_at >= '2022-04-01' AND planned_at < '2022-05-01'
  AND user_id = 1;

-- id:1 山田さんの2022年4月7日のスケジュールを取得
SELECT * FROM plans
WHERE
  planned_at >= '2022-04-07' AND planned_at < '2022-04-08'
  AND user_id = 1;