import { XPArduinoPage } from './app.po';

describe('xparduino App', () => {
  let page: XPArduinoPage;

  beforeEach(() => {
    page = new XPArduinoPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
