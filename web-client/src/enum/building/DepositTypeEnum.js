const DEPOSIT = 'DEPOSIT';
const RETURN = 'RETURN';
const USE = 'USE';

function getDisplayText(value) {
  if (!value) {
    return '';
  }

  if (value === 'DEPOSIT') {
    return '存款';
  }

  if (value === 'RETURN') {
    return '退还';
  }

  if (value === 'USE') {
    return '还款';
  }
}

export default {
  DEPOSIT,
  RETURN,
  USE,
  getDisplayText,
};
