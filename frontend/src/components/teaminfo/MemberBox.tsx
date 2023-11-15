import "./MemberBox.css"

type MemberBoxProps = {
  imgName: string;
  name: string;
  position: string;
  msg: string;
};

function MemberBox({ imgName, name, position, msg }: MemberBoxProps) {
  const imgUrl = process.env.PUBLIC_URL + "/imgs/" + imgName + ".png";

  return (
    <div className="member-box">
      <div className="member-content-one">
        <img src={imgUrl} alt={name} className="member-img" />
      </div>
      <div className="hover-text">{msg}</div>
      <div className="member-content-two">
        <div className="text-s mb-4">{name}</div>
        <div>{position}</div>
      </div>
    </div>
  );
}

export default MemberBox;
