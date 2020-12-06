const { src, dest } = require('gulp');
var replace = require('gulp-replace')

exports.default = function (cb) {
    src(['dist/front/index.html'])
        .pipe(replace('type="module"', 'type="text/javascript"'))
        .pipe(replace('rel="stylesheet"', 'rel="stylesheet" type="text/css"'))
        .pipe(dest('dist/front/'));
    cb();
};
