/** @type {import('tailwindcss').Config} */
module.exports = {
  important: true,
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
        brand: {
          light: '#ff7b7b',
          DEFAULT: '#e94848',
          dark: '#c43535',
        },
        secondary: '#2d3436',
      },
      fontFamily: {
        sans: ['Outfit', 'sans-serif'],
      },
    },
  },
  plugins: [],
}
