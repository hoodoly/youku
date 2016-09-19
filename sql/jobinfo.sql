--存储过程--
DELIMITER $$;

CREATE PROCEDURE procedure_apply_success (IN activity_id bigint, IN phone VARCHAR(20), out r_result int)
  BEGIN
    DECLARE insert_count int DEFAULT 0;
    START TRANSACTION;
    INSERT ignore INTO apply_success (activity_id, phone, activity_name, create_at) VALUE (activity_id, phone, null, now());
    SELECT ROW_COUNT() INTO insert_count;
    IF (insert_count=0) THEN
      ROLLBACK ;
      SET r_result = 0;
    ELSEIF (insert_count<0) THEN
      ROLLBACK ;
      SET r_result = -1;
    ELSE
      UPDATE activity SET number = number-1 WHERE id = activity_id AND number>0;
      SELECT ROW_COUNT() INTO insert_count;
      IF (insert_count < 1) THEN
        ROLLBACK ;
        SET r_result = -2;
      ELSE
        COMMIT ;
        SET r_result = 1;
      END IF;
    END IF;
  END;
$$

DELIMITER ;

SET @r_result = -3;

call procedure_apply_success(1, 15675686548, @r_result);

SELECT @r_result;

drop PROCEDURE procedure_apply_success;
