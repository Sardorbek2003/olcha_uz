  document.addEventListener('DOMContentLoaded', function () {
    const dateFields = document.querySelectorAll('.date-field');
    dateFields.forEach(field => {
      const dateText = field.textContent.trim();
      if (dateText) {
        const date = new Date(dateText);
        field.textContent = date.toISOString().split('T')[0]; // Yil-oy-kun formatida
      }
    });
  });

