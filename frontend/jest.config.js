module.exports = {
  // transform: {"^.+\\.(t|j)sx?$": "ts-jest"},
  // testRegex: "(/__tests__/.*|(\\.|/)(test|spec))\\.(jsx?|tsx?)$",
  // moduleFileExtensions: ["ts", "tsx", "js", "jsx", "json", "node"],
  coverageReporters: ["json", "lcov", 'html', 'text', 'text-summary'],
  coverageDirectory: "./coverage/",
  collectCoverage: true
};