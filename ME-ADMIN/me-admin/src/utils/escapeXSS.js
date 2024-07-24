export function  escapeXSS(input) {
  if (typeof input === 'string') {
    return input.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;");
  } else if (typeof input === 'object') {
    if (Array.isArray(input)) {
      return input.map(item => escapeXSS(item));
    } else {
      const escapedObject = {};
      for (const key in input) {
        if (input.hasOwnProperty(key)) {
          escapedObject[key] = escapeXSS(input[key]);
        }
      }
      return escapedObject;
    }
  } else {
    return input;
  }
}